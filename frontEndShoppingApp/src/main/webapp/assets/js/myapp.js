$(function() {
	// solving the active menu problem

	switch (menu) {

	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;

	case 'Manage Products':
		$('#manageProducts').addClass('active');
		break;

	default:
		if (menu == "Home")
			break;
		$('#listProducts').addClass('active');
		$('#a_' + menu).addClass('active');
		break;

	}
	
	//to tackle the csrf token
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	
	if(token.length > 0 && header.length > 0){
		//set the token header for the ajax request
		$(document).ajaxSend(function(e, xhr, options){
			xhr.setRequestHeader(header,token);
		});
	}

	// code for jquery dataTable
	// create a dataset
	/*
	 * var products = [
	 * 
	 * ['1','ABC'], ['2','SAD'], ['3','ADD'], ['4','AFS'], ['5','SDG'],
	 * ['6','SDC'], ['7','CFF'], ['2','SAD'], ['3','ADD'], ['4','AFS'],
	 * ['5','SDG'], ['6','SDC'], ['7','CFF'], ['8','VDE'] ];
	 */

	var $table = $('#productListTable');

	// execute the below code where we this table
	if ($table.length) {
		// console.log('Inside the Table')
		/*
		 * $table.DataTable({ data: products });
		 */

		var jsonUrl = '';
		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
		} else {
			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/products';
		}

		$table
				.DataTable({
					lengthMenu : [ [ 3, 5, 10, -1 ],
							[ '3 Records', '5 Records', '10 Records', 'All' ] ],
					pageLength : 5,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [

							{
								data : 'code',
								mRender : function(data, type, row) {
									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class="dataTableImg"/>';
								}
							},
							{
								data : 'name'
							},
							{
								data : 'brand'
							},
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '&#36; ' + data
								}
							},
							{
								data : 'quantity',
								mRender : function(data, type, row) {
									if (data < 1) {
										return '<span style="color:red">Out Of Stock</span>';
									}
									return data;
								}
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									str += '<a href="'
											+ window.contextRoot
											+ '/show/'
											+ data
											+ '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';
									if (row.quantity < 1) {
										str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
									} else {
										str += '<a href="'
												+ window.contextRoot
												+ '/cart/add/'
												+ data
												+ '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';

									}

									return str;
								}

							}

					]
				});
	}

	// dismissing the alert after 3 seconds
	var $alert = $('.alert');
	if ($alert.length) {
		setTimeout(function() {
			$alert.fadeOut('slow');
		}, 3000)

	}
	
		
	//------------------------------------
	// 		Data table for Admin
	//------------------------------------
	
	
	var $adminProductsTable = $('#adminProductsTable');

	// execute the below code where we this table
	if ($adminProductsTable.length) {
		
		var jsonUrl = window.contextRoot + '/json/data/admin/all/products';
		

		$adminProductsTable
				.DataTable({
					lengthMenu : [ [ 10, 30, 50, -1 ],
							[ '10 Records', '30 Records', '50 Records', 'All' ] ],
					pageLength : 10,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
						{
							data:'id'
						},

						{
								data : 'code',
								bSortable: false,
								mRender : function(data, type, row) {
									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class="adminDataTableImg"/>';
							}
							},
							{
								data : 'name'
							},
							{
								data : 'brand'
							},
							
							{
								data : 'quantity',
								mRender : function(data, type, row) {
									if (data < 1) {
										return '<span style="color:red">Out Of Stock</span>';
									}
									return data;
								}
							},
							
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '&#36; ' + data
								}
							},
							{
								data : 'active',
								bSortable: false,
								mRender: function(data, type, row){
									
									var str = '';
									
									str += '<label class="switch">';
									if(data){
										str+= '<input type="checkbox" checked="checked" value="'+row.id+'" />';	
									}else{
										str+= '<input type="checkbox" value="'+row.id+'" />';
									}
									
									str += '<div class="slider"> </div></label>';
									
									return str;
									
									
								}
																

							},
							{
								data: 'id',
								bSortable: false,
								mRender: function(data,type,row){
									var str = '';
									
									str += '<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning">';
									str += '<span class="glyphicon glyphicon-pencil"></span></a>';
									
									return str;
								}
							}

					],
					
					initComplete: function(){
						var api = this.api();
						api.$('.switch input[type="checkbox"]').on('change',function(){
							
							var checkbox = $(this);
							var checked = checkbox.prop('checked');
							var dMsg = (checked)? 'Do you want to Activate the product ?':
													'Do you want to De-Activate the product ?';
							var value = checkbox.prop('value');
							
							bootbox.confirm({
								size: 'medium',
								title: 'Product Activation & Deactivation',
								message: dMsg,
								callback: function(confirmed){
									if(confirmed){
										console.log(value);
										
										var activationUrl = window.contextRoot + '/manage/product/'+value+'/activation';
										//short hand method and a call back function
										$.post(activationUrl, function(data){
											bootbox.alert({
												size: 'meduim',
												title: 'Information',
												message: data
											});
											
										});
										
																				
									}else{
										checkbox.prop('checked',!checked)
									}
									
								}
							})
						});
					}
					
				});
	}

	
	
	
	
	function errorPlacement(error, element) {
		// Add the 'help-block' class to the error element
		error.addClass("help-block");
		
		// add the error label after the input element
		error.insertAfter(element);
		
		
		// add the has-feedback class to the
		// parent div.validate in order to add icons to inputs
		element.parents(".validate").addClass("has-feedback");	

	}	
	
	//---------------------------
	// Validation code for category
	
	var $categoryForm = $('#categoryForm');
	
	if($categoryForm.length){
		$categoryForm.validate({
			rules: {
				name: {
					required: true,
					minlength: 2
				},
				description: {
					required: true
				}
				},
				message:{
					
					name: {
					required: 'Please add the category name !',
					minlength: 'The category name should not be less than 2 characters'
					},
					description:{
						required: 'Please add a description for this category !'
					}
			},
			errorElement: 'em',
			errorPlacement: function(error, element){
				//add the class of help-block
				error.addClass('help-block');
				//add the error element after the input element
				error.insertAfter(element);
			}
		});
		
		//-------------------------------------
	}
	

	
// Validation code for loginForm
	
	var $loginForm = $('#loginForm');
	
	if($loginForm.length){
		$loginForm.validate({
			rules: {
				username: {
					required: true,
					email: true
				},
				password: {
					required: true
				}
				},
				message:{					
					username: {
					required: 'Please enter the username !',
					email: 'Please enter valid email address !'
					},
					password:{
						required: 'Please enter the password !'
					}
			},
			errorElement: 'em',
			errorPlacement: function(error, element){
				//add the class of help-block
				error.addClass('help-block');
				//add the error element after the input element
				error.insertAfter(element);
			}
				
	
		});
	
	}
	
});














