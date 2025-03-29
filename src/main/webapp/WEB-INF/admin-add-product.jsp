<div class="container py-4">
  <a href="products" class="btn btn-primary" role="button">View All Products</a>
  <h1>Add Product</h1>
  <c:if test="${not empty productAdded}">
    <div class="alert <c:choose><c:when test="${productAdded == true}">alert-success</c:when><c:when test="${productAdded == false}">alert-danger</c:when><c:otherwise></c:otherwise></c:choose>" role="alert">
        ${productAddedMessage}
    </div>
  </c:if>
  <%-- A Bootstrap row contains a grid of 12 columns --%>
  <form class="row g-3" method="POST" action="add-product">
    <%-- col-md-4 means the column will be 1/3 of the row's width  --%>
    <div class="col-md-4">
      <label for="name" class="form-label">Name</label>
      <input type="text" class="form-control <c:choose><c:when test="${nameError == true}">is-invalid</c:when><c:when test="${nameError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="name" name="name" value="${name}">
      <div class="<c:choose><c:when test="${nameError == true}">invalid-feedback</c:when><c:when test="${nameError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
        ${nameMessage}
      </div>
    </div>
    <%-- col-md-8 means the column will be 2/3 of the row's width  --%>
    <div class="col-md-8">
      <label for="price" class="form-label">Price</label>
      <input type="text" class="form-control <c:choose><c:when test="${priceError == true}">is-invalid</c:when><c:when test="${priceError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="price" name="price" value="${price}">
      <div class="<c:choose><c:when test="${priceError == true}">invalid-feedback</c:when><c:when test="${priceError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
        ${priceMessage}
      </div>
    </div>
    <%-- col-md-3 means the column will be 1/4 of the row's width  --%>
    <div class="col-md-3">
      <label for="description" class="form-label">Description</label>
      <input type="text"  class="form-control <c:choose><c:when test="${descriptionError == true}">is-invalid</c:when><c:when test="${descriptionError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="description" name="description" value="${description}">
      <div class="<c:choose><c:when test="${descriptionError == true}">invalid-feedback</c:when><c:when test="${descriptionError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
        ${descriptionMessage}
      </div>
    </div>
    <%-- col-md-9 means the column will be 3/4 of the row's width  --%>
    <div class="col-md-9">
      <label for="color" class="form-label">Color</label>
      <input type="text" class="form-control <c:choose><c:when test="${colorError == true}">is-invalid</c:when><c:when test="${colorError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="color" name="color" value="${color}">
      <div class="<c:choose><c:when test="${colorError == true}">invalid-feedback</c:when><c:when test="${colorError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
        ${colorMessage}
      </div>
    </div>
    <div class="col-12">
      <button class="btn btn-secondary" type="submit">Submit form</button>
    </div>
  </form>
</div>