<div class="container py-4">
    <a href="${appURL}/add-coach" class="btn btn-primary" role="button">Add Coach</a>
    <div class="row">
        <!-- Main content START -->
        <div class="col-xl-12">
            <!-- Title -->
            <h1>All Coaches</h1>
            <p class="lead">
                <c:choose>
                    <c:when test="${coaches.size() == 1}">There is 1 coach</c:when>
                    <c:otherwise>There are ${coaches.size()} coaches</c:otherwise>
                </c:choose>
            </p>
            <c:if test="${coaches.size() > 0}">
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th scope="col"></th>
                            <th scope="col">First name</th>
                            <th scope="col">Last name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Age</th>
                            <th scope="col">Pronoun</th>
                            <th scope="col">Biography</th>
                            <th scope="col">Specialty</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${coaches}" var="coach">
                        <tr>
                            <td>
                                <a href="${appURL}/edit-coach?id=${coach.coachId}" class="btn btn-sm btn-outline-primary">Update</a>
                                <a href="${appURL}/delete-coach?id=${coach.coachId}" class="btn btn-sm btn-outline-danger">Delete</a>
                            </td>
                            <td>${coach.firstName}</td>
                            <td>${coach.lastName}</td>
                            <td>${coach.email}</td>
                            <td>${coach.age}</td>
                            <td>${coach.pronoun}</td>
                            <td>${coach.biography}</td>
                            <td>${coach.specialty}</td>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div> <%-- Col END --%>
    </div> <%-- Row END --%>
</div> <%-- Container END --%>