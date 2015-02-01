'use strict';

/* App Module */
var recipeExtenderApp = angular.module('recipeExtenderApp', [
    'ngRoute',
    'recipeExtenderControllers',
    'recipeExtenderServices'
]);

recipeExtenderApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
            when('/recipes/add', {
                templateUrl: 'partials/new_recipe.html',
                controller: 'NewRecipeCtrl'
            }).
            when('/recipes/display/:recipeId', {
                templateUrl: 'partials/recipe_display.html',
                controller: 'RecipeDisplayCtrl'
            }).
            otherwise({
                redirectTo: '/recipes/add'
            });
    }]);