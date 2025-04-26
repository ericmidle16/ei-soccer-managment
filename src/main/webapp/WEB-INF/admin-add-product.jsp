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
      <div class="col-md-4">
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
      <div class="col-md-6">
        <!-- Pronoun Preference -->
        <label class="form-label" for="category">Category</label>
        <select class="<c:choose><c:when test="${categoryError == true}">is-invalid</c:when><c:when test="${categoryError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose> form-select js-choice z-index-9 bg-transparent" aria-label=".form-select-sm" id="category" name="category">
          <option value="1" <c:if test="${categoryId == '1'}">selected</c:if>>Ball</option>
          <option value="2" <c:if test="${categoryId == '2'}">selected</c:if>>T-shirt</option>
          <option value="3" <c:if test="${categoryId == '3'}">selected</c:if>>Water Bottle</option>
        </select>
        <div class="<c:choose><c:when test="${categoryError == true}">invalid-feedback</c:when><c:when test="${categoryError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
          ${categoryMessage}
        </div>
      </div>
    <%-- col-md-9 means the column will be 3/4 of the row's width  --%>
      <div class="col-md-6">
        <!-- Pronoun Preference -->
        <label class="form-label" for="color">Color</label>
        <select class="<c:choose><c:when test="${colorError == true}">is-invalid</c:when><c:when test="${colorError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose> form-select js-choice z-index-9 bg-transparent" aria-label=".form-select-sm" id="color" name="color">
          <option value="1" <c:if test="${colorId == '1'}">selected</c:if>>Red</option>
          <option value="2" <c:if test="${colorId == '2'}">selected</c:if>>Blue</option>
          <option value="3" <c:if test="${colorId == '3'}">selected</c:if>>Green</option>
          <option value="4" <c:if test="${colorId == '4'}">selected</c:if>>Black</option>
          <option value="5" <c:if test="${colorId == '5'}">selected</c:if>>White</option>
          <option value="6" <c:if test="${colorId == '6'}">selected</c:if>>Orange</option>
          <option value="7" <c:if test="${colorId == '7'}">selected</c:if>>Gray</option>
          <option value="8" <c:if test="${colorId == '8'}">selected</c:if>>Yellow</option>
          <option value="9" <c:if test="${colorId == '9'}">selected</c:if>>Purple</option>
        </select>
        <div class="<c:choose><c:when test="${colorError == true}">invalid-feedback</c:when><c:when test="${colorError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
          ${colorMessage}
        </div>
      </div>
    <div class="col-12">
      <button class="btn btn-secondary" type="submit">Submit form</button>
    </div>
  </form>
</div>