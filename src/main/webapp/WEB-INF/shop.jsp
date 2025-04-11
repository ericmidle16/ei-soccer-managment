<div class="container py-4">
  <div class="row g-4">
    <c:forEach items ="${products}" var="product">
      <%-- Full width on mobile, half width on tablet, one-third width on laptop, one-forth width on desktop --%>
      <div class="col-sm-12 col-md-6 col-lg-4 col-xl-3">
        <div class="card shadow-sm">
          <div class="card-header">
            <h4>${product.name}</h4>
          </div>
          <div class="card-body">
            <p class="card-text">${product.description}</p>
            <div class="d-flex justify-content-between align-items-center">
              <small class="text-body-secondary"><fmt:formatNumber value="${product.price}" type="currency"/></small>
              <form method="POST" action="${appURL}/add-to-cart" class="w-50">
                <input type="hidden" name="prod_id" value="${product.productId}">
                <div class="input-group">
                  <div class="form-floating mb-3">
                    <input type="number" min="0" class="form-control" id="qty" name="qty" value="1">
                    <label for="qty">Qty</label>
                  </div>
                  <button type="submit" class="btn btn-outline-primary btn sm">Add to Cart</button>
                </div>
              </form>
            </div>
          </div> <%-- Closing card-body --%>
        </div> <%-- Closing card --%>
      </div> <%-- Closing col --%>
    </c:forEach>
  </div><%-- Closing row --%>
</div><%-- Closing container --%>
