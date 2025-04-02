<div class="container py-4">
  <a href="${appURL}/add-product" class="btn btn-primary" role="button">Add Product</a>
  <div class="row">
    <!-- Main content START -->
    <div class="col-xl-12">
      <!-- Title -->
      <h1>All Products</h1>
      <p class="lead">
        <c:choose>
          <c:when test="${products.size() == 1}">There is 1 product</c:when>
          <c:otherwise>There are ${products.size()} products</c:otherwise>
        </c:choose>
      </p>
      <c:if test="${products.size() > 0}">
        <div class="table-responsive">
          <table class="table table-bordered">
            <thead>
            <tr>
              <th scope="col"></th>
              <th scope="col">Name</th>
              <th scope="col">Price</th>
              <th scope="col">Description</th>
              <th scope="col">Color</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}" var="product">
            <tr>
              <td>
                <a href="${appURL}/edit-product?id=${product.productId}" class="btn btn-sm btn-outline-primary">Update</a>
                <button type="button" class="btn btn-sm btn-outline-danger" data-bs-toggle="modal" data-bs-target="#productDeleteModal" data-productid="${product.productId}">
                  Delete
                </button>
              </td>
              <td>${product.name}</td>
              <td><fmt:formatNumber value="${product.price}" type="currency" /></td>
              <td>${product.description}</td>
              <td>${product.color}</td>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </c:if>
    </div> <%-- Col END --%>
  </div> <%-- Row END --%>
</div> <%-- Container END --%>
<%@ include file="/WEB-INF/admin-delete-product.jspf" %>

