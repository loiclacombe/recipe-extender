'use strict';

/* Controllers */
function addIngredient(){
    this.push({ingredient: ""});
}

function addStep(){
    this.push({step: ""});
}

function removeLast(){
    if(this.length > 1){
        var lastElementIndex = this.length - 1;
        this.splice(lastElementIndex, 1);
    }
}


function makeCustomArray(size, addEltFunc) {
    var result = [];
    result.add = addEltFunc;
    result.removeLast = removeLast;
    for(var i=0; i<size; i++) result.add();
    return result;
}


var recipeExtenderControllers = angular.module('recipeExtenderControllers', []);

recipeExtenderControllers.controller('NewRecipeCtrl', function ($scope) {
    $scope.INITIAL_INGREDIENT_COUNT = 5;
    $scope.INITIAL_STEP_COUNT = 5;

    $scope.ingredients = makeCustomArray($scope.INITIAL_INGREDIENT_COUNT, addIngredient);
    $scope.steps = makeCustomArray($scope.INITIAL_STEP_COUNT, addStep);
    $scope.showDebug="";

    $scope.updateDebug = function(){
        $scope.showDebug="";
    };


});




