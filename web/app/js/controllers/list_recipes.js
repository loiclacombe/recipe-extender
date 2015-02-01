'use strict';


var recipeExtenderControllers = angular.module('recipeExtenderControllers', []);

recipeExtenderControllers.controller('ListRecipesCtrl', [
    '$scope', '$routeParams', 'Recipes',function ($scope, $routeParams, Recipes) {
    $scope.recipes = Recipes.get({userId: $routeParams.userId}, function(allRecipes){
        return allRecipes;
    });

}]);