<div class="container py-4">
  <a href="coaches" class="btn btn-primary" role="button">View All Coaches</a>
  <h1>Add Coach</h1>
  <c:if test="${not empty coachAdded}">
    <div class="alert <c:choose><c:when test="${coachAdded == true}">alert-success</c:when><c:when test="${coachAdded == false}">alert-danger</c:when><c:otherwise></c:otherwise></c:choose>" role="alert">
        ${coachAddedMessage}
    </div>
  </c:if>
  <%-- A Bootstrap row contains a grid of 12 columns --%>
  <form class="row g-3" method="POST" action="add-coach">
    <%-- col-md-4 means the column will be 1/3 of the row's width  --%>
    <div class="col-md-4">
      <label for="firstName" class="form-label">First Name</label>
      <input type="text" class="form-control <c:choose><c:when test="${firstNameError == true}">is-invalid</c:when><c:when test="${firstNameError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="firstName" name="firstName" value="${firstName}">
      <div class="<c:choose><c:when test="${firstNameError == true}">invalid-feedback</c:when><c:when test="${firstNameError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
        ${firstNameMessage}
      </div>
    </div>
    <%-- col-md-8 means the column will be 2/3 of the row's width  --%>
    <div class="col-md-8">
      <label for="lastName" class="form-label">Last Name</label>
      <input type="text" class="form-control <c:choose><c:when test="${lastNameError == true}">is-invalid</c:when><c:when test="${lastNameError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="lastName" name="lastName" value="${lastName}">
      <div class="<c:choose><c:when test="${lastNameError == true}">invalid-feedback</c:when><c:when test="${lastNameError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
        ${lastNameMessage}
      </div>
    </div>
    <%-- col-md-3 means the column will be 1/4 of the row's width  --%>
    <div class="col-md-3">
      <label for="email" class="form-label">Email</label>
      <input type="text"  class="form-control <c:choose><c:when test="${emailError == true}">is-invalid</c:when><c:when test="${emailError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="email" name="email" value="${email}">
      <div class="<c:choose><c:when test="${emailError == true}">invalid-feedback</c:when><c:when test="${emailError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
        ${emailMessage}
      </div>
    </div>
    <%-- col-md-9 means the column will be 3/4 of the row's width  --%>
    <div class="col-md-9">
      <label for="age" class="form-label">Age</label>
      <input type="text" class="form-control <c:choose><c:when test="${ageError == true}">is-invalid</c:when><c:when test="${ageError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="age" name="age" value="${age}">
      <div class="<c:choose><c:when test="${ageError == true}">invalid-feedback</c:when><c:when test="${ageError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
        ${ageMessage}
      </div>
    </div>
      <div class="col-md-6">
        <!-- Pronoun Preference -->
        <label class="form-label" for="pronoun">Pronouns</label>
        <select class="<c:if test="${not empty pronounError}">is-invalid</c:if> form-select js-choice z-index-9 bg-transparent" aria-label=".form-select-sm" id="pronoun" name="pronoun">
          <option value="He/Him" ${Coach.pronoun == 'He/Him' ? 'selected' : ''}>He/Him</option>
          <option value="She/Her" ${Coach.pronoun == 'She/Her' ? 'selected' : ''}>She/Her</option>
          <option value="Other" ${Coach.pronoun == 'Other' ? 'selected' : ''}>Other</option>
        </select>
        <c:if test="${not empty pronounError }"><div class="invalid-feedback">${pronounError}</div></c:if>
      </div>
    <div class="col-md-4">
      <label for="biography" class="form-label">Biography</label>
      <input type="text" class="form-control <c:choose><c:when test="${biographyError == true}">is-invalid</c:when><c:when test="${biographyError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="biography" name="biography" value="${biography}">
      <div class="<c:choose><c:when test="${biographyError == true}">invalid-feedback</c:when><c:when test="${biographyError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
        ${biographyMessage}
      </div>
    </div>
    <div class="col-md-4">
      <label for="specialty" class="form-label">Specialty</label>
      <input type="text" class="form-control <c:choose><c:when test="${specialtyError == true}">is-invalid</c:when><c:when test="${specialtyError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>" id="specialty" name="specialty" value="${specialty}">
      <div class="<c:choose><c:when test="${specialtyError == true}">invalid-feedback</c:when><c:when test="${specialtyError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
        ${specialtyMessage}
      </div>
    </div>
    <div class="col-12">
      <button class="btn btn-secondary" type="submit">Submit form</button>
    </div>
  </form>
</div>
