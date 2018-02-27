<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="container">
	<div class="row">

		<c:if test="${not empty message}">
			<div class="col-xs-12">

				<div class="alert alert-success alert-dismissible">

					<button type="button" class="close" data-dismiss="alert">
						&times;</button>
					${message}
				</div>
			</div>
		</c:if>
		<div class="col-md-offset-2 col-md-8">

			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Product Management</h4>
				</div>

				<div class="panel-body">
					<!-- Form Element -->
					<sf:form class="form-horizontal" modelAttribute="product"
						action="${contextRoot}/manage/products" method="POST"
						enctype="multipart/form-data">
						<div class="form-group">
							<label class="control-label col-md-4" for="name">Enter
								Product Name : </label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="name"
									placeholder="Product Name" class="form-control" />
								<sf:errors path="name" cssClass="help-block" element="em" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4" for="brand">Enter
								Brand Name : </label>
							<div class="col-md-8">
								<sf:input type="text" path="brand" id="brand"
									placeholder="Brand Name" class="form-control" />
								<sf:errors path="brand" cssClass="help-block" element="em" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="description">Product
								Description: </label>
							<div class="col-md-8">
								<sf:textarea path="description" id="description" rows="4"
									placeholder="Write a description"></sf:textarea>
								<sf:errors path="description" cssClass="help-block" element="em" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="unitPrice">Enter
								Unit Price : </label>
							<div class="col-md-8">
								<sf:input type="number" path="unitPrice" id="unitPrice"
									placeholder="Unit Price in &#36;"></sf:input>
								<sf:errors path="unitPrice" cssClass="help-block" element="em" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="quantity">Quantity
								Available : </label>
							<div class="col-md-8">
								<sf:input type="number" path="quantity" id="quantity"
									placeholder="Quantity Available" />
								<sf:errors path="quantity" cssClass="help-block" element="em" />
							</div>
						</div>

						<!-- File element image upload -->
						<div class="form-group">
							<label class="control-label col-md-4" for="file">Select
								an Image : </label>
							<div class="col-md-8">
								<sf:input type="file" path="file" id="file" class="form-control" />
								<sf:errors path="file" cssClass="help-block" element="em" />
							</div>
						</div>


						<div class="form-group">
							<label class="control-label col-md-4" for="categoryId">Select
								Category :</label>
							<div class="col-md-4">
								<sf:select class="form-control" id="categoryId"
									path="categoryId" items="${categories}" itemLabel="name"
									itemValue="id" />
									
									<c:if test="${product.id == 0}">
										<div class="text-right">						
										<br/>
										<button type="button" data-toggle="modal" data-target="#myCategoryModal" class="btn btn-warning btn-xs">Add Category</button>
										</div>
									</c:if>

							</div>
						</div>
						

						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<input type="submit" name="submit" id="submit" value="Submit"
									class="btn btn-primary" />
								<sf:hidden path="id" />
								<sf:hidden path="code" />
								<sf:hidden path="supplierId" />
								<sf:hidden path="active" />
								<sf:hidden path="purchases" />
								<sf:hidden path="views" />

							</div>
						</div>

					</sf:form>
				</div>

			</div>
		</div>

		<div class="col-xs-12">
			<h3>Available Products</h3>
			</hr>
		</div>
		<div class="col-xs-12">
			<div style="overflow: auto">
			
			<!-- Products table for Admin -->
			<table id="adminProductsTable" class="table table-condensed table-bordered">
							
				<thead>					
					<tr>					
						<th>Id</th>
						<th>&#160;</th>
						<th>Name</th>
						<th>Brand</th>
						<th>Qty. Avail</th>
						<th>Unit Price</th>
						<th>Activate</th>				
						<th>Edit</th>
					</tr>					
				</thead>
				
	<%-- 	<!-- Commented as we are getting it from the database using jquery-->
	
		<tbody>
				<tr>
				<td>11</td>
				<td>
						<img class="adminDataTableImg" src="${contextRoot}/resources/images/PRDTVSHD.jpg" alt="Sony HD4K"/>
				</td>
				<td>Sony HD4K</td>
				<td>Sony</td>
				<td>6</td>
				<td>&#36; 450.00</td>
				<td>
				//<!-- Toggle Switch -->
					<label class="switch">
						<input type="checkbox" checked="checked" value="11"/>
						<div class="slider"></div>
					</label>
				</td>
				<td>
					<a href="${contextRoot}/manage/11/product" class="btn btn-warning">
					<span class="glyphicon glyphicon-pencil"></span>
					</a>
				</td>
				
				</tr>
				
				
				<tr>
					<td>11</td>
					<td>
						<img class="adminDataTableImg" src="${contextRoot}/resources/images/PRDTVSHD.jpg" alt="Sony HD4K"/>
					</td>
				<td>Sony HD4K</td>
				<td>Sony</td>
				<td>6</td>
				<td>&#36; 450.00</td>
				<td>
				<!-- Toggle Switch -->
					<label class="switch">
						<input type="checkbox" value="11"/>
						<div class="slider"></div>
					</label>
				</td>
				<td>
					<a href="${contextRoot}/manage/11/product" class="btn btn-warning">
					<span class="glyphicon glyphicon-pencil"></span>
					</a>
				</td>
				
				</tr>
				
				
				</tbody> --%>
	
				<tfoot>
					<tr>					
						<th>Id</th>
						<th>&#160;</th>
						<th>Name</th>
						<th>Brand</th>
						<th>Qty. Avail</th>
						<th>Unit Price</th>
						<th>Activate</th>				
						<th>Edit</th>
					</tr>									
				</tfoot>
				
							
			</table>
		
			
			
			
			</div>
		</div>
	</div>


<div class="modal fade" id="myCategoryModal" role="dialog" tabindex="-1">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
		<!-- Modal Header -->
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span>&times;</span>
				</button>
				<h4 class="modal-title">Add New Category</h4>
			</div>
			<div class="modal-body">
			<!-- Category form -->
			<sf:form id="categoryForm" modelAttribute="category" action="${contextRoot}/manage/category" method="POST" class="form-horizontal">
				<div class="form-group">
					<label for="category_name" class="control-label col-md-4">Category Name</label>
					<div class="col-md-8">
						<sf:input type="text" path="name" id="category_name" class="form-control"/>
					</div>
				</div>
				
				<div class="form-group">
					<label for="category_description" class="control-label col-md-4">Category Description</label>
					<div class="col-md-8">
						<sf:textarea cols="" rows="5" path="description" id="category_description" class="form-control"/>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-md-offset-4 col-md-8">
						<input type="submit" value="Add Category" class="btn btn-primary"/>
					</div>
				</div>
				
			</sf:form>
			
			</div>
		</div>
	</div>
</div>


</div>