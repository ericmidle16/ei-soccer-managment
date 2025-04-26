
<div class="container py-4">
  <div class="col d-flex justify-content-between align-items-center">
    <h2 class="mb-4">Get some stellar Merch!</h2>
    <!-- Responsive toggler START -->
    <button class="btn btn-primary d-lg-none" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasSidebar" aria-controls="offcanvasSidebar">
      <i class="bi bi-list fs-4"></i>
    </button>
    <!-- Responsive toggler END -->
  </div>
  <div class="row">
    <!-- Main content START -->
    <div class="col-lg-9">
      <div class="col d-flex justify-content-between align-items-center">
        <p class="lead">Showing products ${firstProductShown}-${lastProductShown} of ${totalProducts}</p>
        <%@include file="/WEB-INF/pagination.jspf"%>
      </div>
      <div class="row g-4">
        <c:forEach items="${products}" var="product">
          <%-- 12 means full-width, 6 means half-width, 4 means one-third width, 3 means one-forth width   --%>
          <div class="col-xs-12 col-sm-6 col-md-4">
            <div class="card shadow-sm">
              <div class="card-header py-2">
                <h4 class="my-0 text-center">${product.name}</h4>
              </div>
              <div class="card-body">
                <p class="badge rounded-pill text-bg-secondary"><a class="text-white" href="${appURL}/shop?categories=${product.categoryId}">${product.categoryName}</a></p>
                <p class="badge rounded-pill text-bg-secondary"><a class="text-white" href="${appURL}/shop?colors=${product.colorId}">${product.colorName}</a></p>
                <p class="card-text">${product.description}</p>
                <div class="d-flex justify-content-between align-items-center">
                  <small class="fw-bold"><fmt:formatNumber value="${product.price}" type="currency" /></small>
                  <a href="${appURL}/add-to-cart?prod_id=${product.productId}" class="btn btn-secondary">Add to Cart</a>
                </div>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>  <!-- End PRODUCT row -->
      <div class="col d-flex justify-content-end align-items-center mt-4">
        <%@include file="/WEB-INF/pagination.jspf"%>
      </div>
    </div><!-- Main content END -->
    <%@include file="shop-sidebar.jspf"%>
  </div><!-- Row END -->
</div><!-- Container END -->

