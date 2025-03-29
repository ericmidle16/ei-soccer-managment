<div class="container py-4">
  <a href="products" class="btn btn-primary" role="button">View All Products</a>
  <h1>Update Product</h1>
  <c:choose>
    <c:when test="${empty product}">
      <p class="lead">Product not found</p>
    </c:when>
    <c:otherwise>
      <c:if test="${not empty productUpdated}">
        <div class="alert <c:choose><c:when test="${productUpdated == true}">alert-success</c:when><c:when test="${productUpdated == false}">alert-danger</c:when><c:otherwise></c:otherwise></c:choose>" role="alert">
            ${productUpdatedMessage}
        </div>
      </c:if>
      <%-- A Bootstrap row contains a grid of 12 columns --%>
      <form class="row g-3" method="POST" action="edit-product?id=${id}">
          <%-- col-md-4 means the column will be 1/3 of the row's width  --%>
        <div class="col-md-4">
          <label for="productId" class="form-label">ID</label>
          <input disabled type="text" class="form-control <c:choose><c:when test="${productIdError == true}">is-invalid</c:when><c:when test="${productIdError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="productId" name="productId" value="${product.productId}">
          <div class="<c:choose><c:when test="${productIdError == true}">invalid-feedback</c:when><c:when test="${productIdError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
              ${productIdMessage}
          </div>
        </div>
          <%-- col-md-8 means the column will be 2/3 of the row's width  --%>
        <div class="col-md-8">
          <label for="name" class="form-label">Name</label>
          <input type="text" class="form-control <c:choose><c:when test="${nameError == true}">is-invalid</c:when><c:when test="${nameError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="name" name="name" value="${product.name}">
          <div class="<c:choose><c:when test="${nameError == true}">invalid-feedback</c:when><c:when test="${nameError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
              ${nameError}
          </div>
        </div>
          <%-- col-md-3 means the column will be 1/4 of the row's width  --%>
        <div class="col-md-3">
          <label for="price" class="form-label">Price</label>
          <input type="text" class="form-control <c:choose><c:when test="${priceError == true}">is-invalid</c:when><c:when test="${priceError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="price" name="price" value="${product.price}">
          <div class="<c:choose><c:when test="${priceError == true}">invalid-feedback</c:when><c:when test="${priceError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
              ${priceMessage}
          </div>
        </div>
          <%-- col-md-9 means the column will be 3/4 of the row's width  --%>
        <div class="col-md-9">
          <label for="description" class="form-label">Description</label>
          <input type="text" class="form-control <c:choose><c:when test="${descriptionError == true}">is-invalid</c:when><c:when test="${descriptionError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="description" name="description" value="${product.description}">
          <div class="<c:choose><c:when test="${descriptionError == true}">invalid-feedback</c:when><c:when test="${descriptionError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
              ${descriptionMessage}
          </div>
        </div>
        <div class="col-md-4">
          <label for="color" class="form-label">Color</label>
          <input type="text" class="form-control <c:choose><c:when test="${colorError == true}">is-invalid</c:when><c:when test="${colorError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="color" name="color" value="${product.color}">
          <div class="<c:choose><c:when test="${colorError == true}">invalid-feedback</c:when><c:when test="${colorError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
              ${colorMessage}
          </div>
        </div>
        <input type="hidden" name="id" value="${id}">
        <div class="col-12">
          <button class="btn btn-secondary" type="submit">Submit form</button>
        </div>
      </form>
    </c:otherwise>
  </c:choose>
</div>