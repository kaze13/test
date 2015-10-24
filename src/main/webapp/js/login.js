$(function(){
	var fm = new form();
	
	
});

var form = function(){
	this.$username = $('#username');
	this.$password = $('#password');
	this.$btn = $('#submit');
	
	this.init();
	
};

form.prototype = {
	submit : function(){
		var username = this.$username.val();
		var password = this.$password.val();
		
		$.post("/Move/verify", { username: username, password: password })
		  .done(function( data ) {
		    alert( "Data Loaded: " + data );
		  });
	},
	
	init : function(){
		this.$username.val('');
		this.$password.val('');
		
		var self=  this;

	}
};
