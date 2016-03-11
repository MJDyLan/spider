/**
 * 主入口
 */
require.config({
	paths:{
		"jquery":"js/jquery",
			"aa":"js/aa"
	}
});

/*require(["math2"],function(a){
	alert(a.add(10,5));
	alert(a.plus(10,5));
	alert(a.multi(10,5));
	alert(a.divide(10,5));
});*/
require(["math"],function(a){
	alert(a.add(10,5));
	alert(a.kk(10,5));

});