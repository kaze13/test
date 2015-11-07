var inventoryApp = angular.module('inventoryApp', []);

inventoryApp.controller('inventoryCtrl', function ($scope) {
	$scope.count = 0;
	
	function sortOn( collection, name ) {
		collection.sort(
		function( a, b ) {
			if ( a[ name ] <= b[ name ] ) {
				return( -1 );
			}
			return( 1 );
		});
	}
	
	function processGroup(list, groups, category) {
		sortOn(list, 'category');
		
		var group = {};
		for(var i=0; i < list.length; ++i) {
			var item = list[i];
			
			if(group.name && group.category){
				if(group.category !== item.category) {
					if((typeof category === 'undefined' || category < 0) || group.category === category)
						groups.push(group);
					group = {category: item.category,
							 name: item.categoryName,
							 list : []};
				}
			} else {
				group = {category: item.category,
						 name: item.categoryName,
						 list : []};
			}
			group.list.push(item);
		}
		if((typeof category === 'undefined' || category < 0) || group.category == category)
		groups.push(group);
	}
	
	$scope.inventoryList = [{name:"desk",desc:'desc1', count: 0, category:1, categoryName: '客厅'},
	                        {name:"chair",desc:'desc2', count: 0, category:1, categoryName: '客厅'},
	                        {name:"Chopsticks",desc:'desc2', count: 0, category:2, categoryName: '厨房'},
	                        {name:"Table",desc:'desc2', count: 0, category:2, categoryName: '厨房'}
	                        ];
	$scope.groups = [];
	
	processGroup($scope.inventoryList, $scope.groups);
	$scope.items = [];
	for(var i=0;i<$scope.groups.length;++i){
		$scope.items.push({name: $scope.groups[i].name, index: $scope.groups[i].category, select: false});
	}
	
	
	$scope.addCount = function(item){
		item.count += 1;
		$scope.count += 1;
	}
	
	$scope.minusCount = function(item){
		if(item.count > 0){
			item.count -= 1;
			if($scope.count > 0)
				$scope.count -= 1;
		}
	}
	$scope.all = true;
	
	$scope.selectMenu = function(category){
		$scope.groups = [];
		processGroup($scope.inventoryList, $scope.groups, category);
		
		for(var i=0;i<$scope.items.length;++i){
			$scope.items[i].select = false;
		}
		$scope.all = true;
		
		if(category > 0) {
			for(var i=0;i<$scope.items.length;++i){
				if($scope.items[i].index === category){
					$scope.items[i].select = true;
					$scope.all = false;
				}
			}
		}
	}
	
})
.directive('inventoryList', function(){
	return {
	    templateUrl: '/Move/template/item.html'
	  };
})
.directive('inventoryFilter', function(){
	return {
		templateUrl: '/Move/template/inventory-filter.html'
	}
})
;
;