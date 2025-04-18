<div class="container py-4">
    <a href="coaches" class="btn btn-primary" role="button">View All Coaches</a>
    <h1>Update Coach</h1>
    <c:choose>
        <c:when test="${empty coach}">
            <p class="lead">Coach not found</p>
        </c:when>
        <c:otherwise>
            <c:if test="${not empty coachUpdated}">
                <div class="alert <c:choose><c:when test="${coachUpdated == true}">alert-success</c:when><c:when test="${coachUpdated == false}">alert-danger</c:when><c:otherwise></c:otherwise></c:choose>"
                     role="alert">
                        ${coachUpdatedMessage}
                </div>
            </c:if>
            <%-- A Bootstrap row contains a grid of 12 columns --%>
            <form class="row g-3" method="POST" action="edit-coach?id=${id}">
                    <%-- col-md-4 means the column will be 1/3 of the row's width  --%>
                <div class="col-md-4">
                    <label for="coachId" class="form-label">ID</label>
                    <input disabled type="text"
                           class="form-control <c:choose><c:when test="${coachIdError == true}">is-invalid</c:when><c:when test="${coachIdError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>"
                           id="coachId" name="coachId" value="${coach.coachId}">
                    <div class="<c:choose><c:when test="${coachIdError == true}">invalid-feedback</c:when><c:when test="${coachIdError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
                            ${coachIdMessage}
                    </div>
                </div>
                    <%-- col-md-8 means the column will be 2/3 of the row's width  --%>
                <div class="col-md-8">
                    <label for="firstName" class="form-label">First Name</label>
                    <input type="text"
                           class="form-control <c:choose><c:when test="${firstNameError == true}">is-invalid</c:when><c:when test="${firstNameError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>"
                           id="firstName" name="firstName" value="${coach.firstName}">
                    <div class="<c:choose><c:when test="${firstNameError == true}">invalid-feedback</c:when><c:when test="${firstNameError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
                            ${firstNameError}
                    </div>
                </div>
                    <%-- col-md-3 means the column will be 1/4 of the row's width  --%>
                <div class="col-md-3">
                    <label for="lastName" class="form-label">Last Name</label>
                    <input type="text"
                           class="form-control <c:choose><c:when test="${lastNameError == true}">is-invalid</c:when><c:when test="${lastNameError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>"
                           id="lastName" name="lastName" value="${coach.lastName}">
                    <div class="<c:choose><c:when test="${lastNameError == true}">invalid-feedback</c:when><c:when test="${lastNameError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
                            ${lastNameMessage}
                    </div>
                </div>
                    <%-- col-md-9 means the column will be 3/4 of the row's width  --%>
                <div class="col-md-9">
                    <label for="email" class="form-label">Email</label>
                    <input type="text"
                           class="form-control <c:choose><c:when test="${emailError == true}">is-invalid</c:when><c:when test="${emailError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>"
                           id="email" name="email" value="${coach.email}">
                    <div class="<c:choose><c:when test="${emailError == true}">invalid-feedback</c:when><c:when test="${emailError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
                            ${emailMessage}
                    </div>
                </div>
                <div class="col-md-4">
                    <label for="age" class="form-label">Age</label>
                    <input type="text"
                           class="form-control <c:choose><c:when test="${ageError == true}">is-invalid</c:when><c:when test="${ageError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>"
                           id="age" name="age" value="${coach.age}">
                    <div class="<c:choose><c:when test="${ageError == true}">invalid-feedback</c:when><c:when test="${ageError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
                            ${ageMessage}
                    </div>
                </div>
                <div class="col-md-6">
                    <!-- Pronoun Preference -->
                    <label class="form-label" for="pronoun">Pronouns</label>
                    <select class="<c:choose><c:when test="${pronounError == true}">is-invalid</c:when><c:when test="${pronounError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose> form-select js-choice z-index-9 bg-transparent" aria-label=".form-select-sm" id="pronoun" name="pronoun">
                        <option value="He/Him" <c:if test="${coach.pronoun == 'He/Him'}">selected</c:if>>He/Him</option>
                        <option value="She/Her" <c:if test="${coach.pronoun == 'She/Her'}">selected</c:if>>She/Her</option>
                        <option value="Other" <c:if test="${coach.pronoun == 'Other'}">selected</c:if>>Other</option>
                    </select>
                    <div class="<c:choose><c:when test="${pronounError == true}">invalid-feedback</c:when><c:when test="${pronounError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
                            ${pronounMessage}
                    </div>
                </div>
                <div class="col-md-4">
                    <label for="biography" class="form-label">Biography</label>
                    <input type="text"
                           class="form-control <c:choose><c:when test="${biographyError == true}">is-invalid</c:when><c:when test="${biographyError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>"
                           id="biography" name="biography" value="${coach.biography}">
                    <div class="<c:choose><c:when test="${biographyError == true}">invalid-feedback</c:when><c:when test="${biographyError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
                            ${biographyMessage}
                    </div>
                </div>
                <div class="col-md-4">
                    <label for="specialty" class="form-label">Specialty</label>
                    <input type="text"
                           class="form-control <c:choose><c:when test="${specialtyError == true}">is-invalid</c:when><c:when test="${specialtyError == false}">is-valid</c:when><c:otherwise></c:otherwise></c:choose>"
                           id="specialty" name="specialty" value="${coach.specialty}">
                    <div class="<c:choose><c:when test="${specialtyError == true}">invalid-feedback</c:when><c:when test="${specialtyError == false}">valid-feedback</c:when><c:otherwise></c:otherwise></c:choose>">
                            ${specialtyMessage}
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