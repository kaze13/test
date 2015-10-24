$(function(){
	var fm = new form();
	
});

var form = function(){
	this.$username = $('#username');
	this.$password = $('#password');
	this.$password_confirm = $('#password_confirm');
	
	this.$btn = $('#submit');
	
	this.init();
	
};

form.prototype = {
	submit : function(){
		var username = this.$username.val();
		var password = this.$password.val();
		var password_confirm = this.$password.val();
		
		$.post("/Move/verify", { username: username, password: password })
		  .done(function( data ) {
		    
		  });
	},
	
	init : function(){
		this.$username.val('');
		this.$password.val('');
		this.$password_confirm.val('');
		
		var self=  this;

	}
};