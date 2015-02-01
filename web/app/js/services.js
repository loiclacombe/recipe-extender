var recipeExtenderServices = angular.module('recipeExtenderServices', ['ngResource']);

recipeExtenderServices.factory('Recipes', ['$resource',
    function($resource){
        return $resource('rest/recipes/:userId.json', {}, {
            query: {method:'GET', params:{userId: 'userId'}, isArray:true}
        });
    }]);