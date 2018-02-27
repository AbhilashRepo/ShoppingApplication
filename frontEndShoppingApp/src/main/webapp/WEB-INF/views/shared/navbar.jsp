
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">

		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#bs-example-navbar-collapse-1"
			aria-controls="navbarResponsive" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<a class="navbar-brand" href="${contextRoot}/home">Shopping App</a>
		<div class="collapse navbar-collapse"
			id="#bs-example-navbar-collapse-1">
			<ul class="navbar-nav ml-auto">
			<!-- 	<li class="nav-item active"><a class="nav-link"
					href="${contextRoot}/home">Home<span class="sr-only">(current)</span> &#160;</a></li> -->
				<li id="about"><a href="${contextRoot}/about">About</a> &#160;</li>
				<li id="contact"><a href="${contextRoot}/contact">Contact</a> &#160;</li>
				<li id="listProducts"><a
					href="${contextRoot}/show/all/products">View Products</a> &#160;</li>
				<li id="manageProducts"><a
					href="${contextRoot}/manage/products">Manage Products</a> &#160;</li>
			</ul>
			
			<ul class="nav navbar-nav navbar-right">
				<li id="register">
				<a href="${contextRoot}/register">Sign Up</a>
				</li>
				<li id="login">
				<a href="${contextRoot}/login">Login</a>
				</li>
			</ul>
			
		</div>
	</div>
</nav>

