angular.module('market-front').controller('storeController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/market-core/';
    let currentPageIndex = 1;

    $scope.loadProducts = function (pageIndex = 1) {
        currentPageIndex = pageIndex;
        $http({
            url: contextPath + 'api/v1/products',
            method: 'GET',
            params: {
                p: pageIndex
            }
        }).then(function (response) {
            console.log(response);
            $scope.productsPage = response.data;
            $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.productsPage.totalPages);
        });
    };

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.deleteProduct = function (product, index) {
        $http({
            url: contextPath + 'api/v1/products/delete/' + encodeURIComponent(product.id),
            method: 'DELETE',
        }).then(
            function (response) {
                console.log(response);
                $scope.productsPage.content.splice(index, 1);
            },
            function (response) {
                console.log(response);
                alert('Не удалось удалить объект');
            }
        );
    };

    $scope.addToCart = function (productId) {
        $http({
            url: 'http://localhost:8191/market-cart/api/v1/cart/' + $localStorage.webMarketGuestCartId + '/add/' + productId,
            method: 'GET'
        }).then(function (response) {
        });
    };


    $scope.navToEditProductPage = function (productId) {
        $location.path('/edit_product/' + productId);
    }

    $scope.loadProducts();
});