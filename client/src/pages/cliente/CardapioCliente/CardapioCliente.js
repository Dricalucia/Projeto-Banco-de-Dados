window.addEventListener("DOMContentLoaded", (event) => {
  const headerContainer = document.getElementById("header-container");
  fetch("../../../utils/HeaderCliente.html")
    .then((response) => response.text())
    .then((data) => {
      headerContainer.innerHTML = data;
    });

  // Verifica se o usuário está logado
  const loggedInUser = JSON.parse(sessionStorage.getItem("loggedInUser"));
  if (!loggedInUser) {
    console.error("Nenhum usuário logado encontrado");
    window.location.href =
      "/Projeto-Banco-de-Dados/client/src/pages/login/cliente/LoginCliente.html";
  } else {
    console.log("Usuário logado encontrado:", loggedInUser);
  }

  // Carregar categorias e itens do banco de dados
  loadCategoriesAndItems();

  // Função para mostrar e esconder filtros
  $("#toggleFilters").on("click", function () {
    $("#filterContainer").toggle();
  });

  // Filtragem de itens
  $("#filterCategory").on("change", filterItems);
  $("#filterName").on("input", filterItems);
  $("#filterPrice").on("input", function () {
    $("#filterPriceValue").text($(this).val());
    filterItems();
  });

  // Abrir modal para adicionar item à sacola
  $("#addItemModal").on("show.bs.modal", function (event) {
    var button = $(event.relatedTarget);
    var item = button.data("item");
    $("#modalItemName").text(item.nomeItem);
    $("#modalItemDescription").text(item.descricaoItem);
    $("#modalItemPrice").text(item.precoItem.toFixed(2));
    $("#itemQuantity").val(1);
    updateTotalPrice(item.precoItem);

    $("#itemQuantity").on("input", function () {
      updateTotalPrice(item.precoItem);
    });

    $("#addToBagButton")
      .off("click")
      .on("click", function () {
        addToBag(item);
      });
  });
});

function loadCategoriesAndItems() {
  $.ajax({
    url: "http://localhost:8080/categorias",
    method: "GET",
    success: function (categories) {
      const categoryMap = categories.reduce((map, category) => {
        map[category.idCategoria] = category.descricao;
        return map;
      }, {});
      populateCategoryFilter(categories);
      // Carregar itens após carregar categorias
      loadItems(categoryMap);
    },
    error: function (err) {
      console.error("Erro ao carregar categorias:", err);
    },
  });
}

function populateCategoryFilter(categories) {
  const filterCategory = $("#filterCategory");
  filterCategory.empty();
  filterCategory.append(new Option("Todas as Categorias", ""));
  categories.forEach((category) => {
    filterCategory.append(new Option(category.descricao, category.idCategoria));
  });
}

function loadItems(categoryMap) {
  $.ajax({
    url: "http://localhost:8080/itens",
    method: "GET",
    success: function (data) {
      renderItems(data, categoryMap);
    },
    error: function (err) {
      console.error("Erro ao carregar itens:", err);
    },
  });
}

function renderItems(items, categoryMap) {
  var itemsPanel = $("#itemsPanel");
  itemsPanel.empty();

  var itemsByCategory = groupItemsByCategory(items);
  for (var categoryId in itemsByCategory) {
    if (itemsByCategory.hasOwnProperty(categoryId)) {
      var categoryName = categoryMap[categoryId] || "Categoria Desconhecida";
      // Adicionar o título da categoria
      var categoryTitle = `<div class="col-10 mx-auto text-center shadow-sm rounded-2 tituloCategoria"><h3>${categoryName}</h3></div>`;
      itemsPanel.append(categoryTitle);

      // Adicionar os itens da categoria
      itemsByCategory[categoryId].forEach(function (item) {
        var itemCard = `
                    <div class="col-md-3 p-3">
                        <div class="card shadow-lg rounded-2 border border-2 border-black">
                            <div class="card-body">
                                <h5 class="card-title nomeProduto">${
                                  item.nomeItem
                                }</h5>
                                <p class="card-text descricaoProduto">${
                                  item.descricaoItem
                                }</p>
                                <div class="row">
                                    <div class="col-md-12">
                                        <h3 class="card-text precoProduto">R$ ${item.precoItem.toFixed(
                                          2
                                        )}</h3>
                                        Unidade
                                    </div>
                                </div>
                                <div class="text-center w-100 p-3">
                                    <button class="btn btn-primary adicionarSacolaButton" type="button" data-bs-toggle="modal" data-bs-target="#addItemModal" data-item='${JSON.stringify(
                                      item
                                    )}'>Adicionar a Sacola</button>
                                </div>
                            </div>
                        </div>
                    </div>`;
        itemsPanel.append(itemCard);
      });
    }
  }
}

function groupItemsByCategory(items) {
  return items.reduce(function (groupedItems, item) {
    var categoryId = item.idCategoria;
    if (!groupedItems[categoryId]) {
      groupedItems[categoryId] = [];
    }
    groupedItems[categoryId].push(item);
    return groupedItems;
  }, {});
}

function filterItems() {
  var selectedCategory = $("#filterCategory").val();
  var name = $("#filterName").val().toLowerCase();
  var price = parseFloat($("#filterPrice").val());

  $("#itemsPanel .card").each(function () {
    var item = $(this).find("button").data("item");
    var match = true;

    if (selectedCategory && item.idCategoria.toString() !== selectedCategory) {
      match = false;
    }
    if (name && !item.nomeItem.toLowerCase().includes(name)) {
      match = false;
    }
    if (price > 0 && item.precoItem > price) {
      match = false;
    }

    if (match) {
      $(this).parent().show();
    } else {
      $(this).parent().hide();
    }
  });
}

function updateTotalPrice(price) {
  var quantity = $("#itemQuantity").val();
  var totalPrice = price * quantity;
  $("#totalPrice").text(totalPrice.toFixed(2));
}

function addToBag(item) {
  var quantity = $("#itemQuantity").val();
  var sacolaItems = JSON.parse(localStorage.getItem("sacola")) || [];

  // Verificar se o item já está na sacola
  var existingItemIndex = sacolaItems.findIndex(
    (sacolaItem) => sacolaItem.idItem === item.idItem
  );

  if (existingItemIndex !== -1) {
    // Atualizar quantidade do item existente
    sacolaItems[existingItemIndex].quantidade += parseInt(quantity);
  } else {
    // Adicionar novo item à sacola
    sacolaItems.push({
      ...item,
      quantidade: parseInt(quantity),
    });
  }

  localStorage.setItem("sacola", JSON.stringify(sacolaItems));

  // Fechar modal e mostrar mensagem de sucesso
  $("#addItemModal").modal("hide");
  alert("Item adicionado à sacola com sucesso!");
}
