<main>

    <div id="myCarousel" class="carousel slide mb-6" data-bs-ride="carousel">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="active" aria-current="true"
                    aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg"
                     aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false">
                    <rect width="100%" height="100%"/>
                    <image href="${appURL}/images/coachtalk2.jpg" width="100%" height="100%"
                           preserveAspectRatio="xMidYMid slice"/>
                </svg>
            </div>
            <div class="carousel-item">
                <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg"
                     aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false">
                    <rect width="100%" height="100%"/>
                    <image href="${appURL}/images/UEFA.jpg" width="100%" height="100%"
                           preserveAspectRatio="xMidYMid slice"/>
                </svg>
                <!-- <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg" aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false"><rect width="100%" height="100%" fill="var(--bs-secondary-color)"/></svg> -->
            </div>
            <div class="carousel-item">
                <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg"
                     aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false">
                    <rect width="100%" height="100%"/>
                    <image href="${appURL}/images/Youth-Soccer-Training.jpg" width="100%" height="100%"
                           preserveAspectRatio="xMidYMid slice"/>
                </svg>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>


    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->


        <!-- START THE FEATURETTES -->

        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading fw-normal lh-1">Need New Merch. <span
                        class="text-body-secondary">Look no further than here!</span></h2>
                <p class="lead">We offer soccer balls, towels, stickers, water bottles!
                </p>
            </div>
            <div class="col-md-5">
                <svg class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500"
                     height="500" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 500x500"
                     preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title>
                    <rect width="100%" height="100%" fill="var(--bs-secondary-bg)"/>
                    <text x="50%" y="50%" fill="var(--bs-secondary-color)" dy=".3em">500x500</text>
                    <image href="${appURL}/images/soccerball.jpg" width="100%" height="100%"
                           preserveAspectRatio="xMidYMid slice"/>
                </svg>
            </div>
        </div>

        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7 order-md-2">
                <h2 class="featurette-heading fw-normal lh-1">We are just chill guys. <span
                        class="text-body-secondary">See for yourself grow.</span></h2>
                <p class="lead">
                    We are chill guys ready to help you grow as a player. Please let us know what you like and dislike.
                    We are open for review!
                </p>
            </div>
            <div class="col-md-5 order-md-1">
                <svg class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500"
                     height="500" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 500x500"
                     preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title>
                    <rect width="100%" height="100%" fill="var(--bs-secondary-bg)"/>
                    <image href="${appURL}/images/chill_guy.png" width="100%" height="100%"
                           preserveAspectRatio="xMidYMid slice"/>
                </svg>
            </div>
        </div>

        <hr class="featurette-divider">



    </div>

    <%@ include file="/WEB-INF/review.jspf" %>

    <hr class="featurette-divider">
    <div class="container mt-5">
        <h2>Frequently Asked Questions</h2>

        <div class="accordion" id="faqAccordion">
            <!-- FAQ Item 1 -->
            <div class="accordion-item">
                <h2 class="accordion-header" id="headingOne">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse"
                            data-bs-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                        What is the pricing for services?
                    </button>
                </h2>
                <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne"
                     data-bs-parent="#faqAccordion">
                    <div class="accordion-body">
                        We price based on your goals. Going into higher leagues or just general skills!
                    </div>
                </div>
            </div>

            <!-- FAQ Item 2 -->
            <div class="accordion-item">
                <h2 class="accordion-header" id="headingTwo">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                        How long does are our services last?
                    </button>
                </h2>
                <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo"
                     data-bs-parent="#faqAccordion">
                    <div class="accordion-body">
                        We generally go on a 4-week cycle. We can go up to 12-week in total.
                    </div>
                </div>
            </div>

            <!-- FAQ Item 3 -->
            <div class="accordion-item">
                <h2 class="accordion-header" id="headingThree">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                            data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                        Do you offer customer support?
                    </button>
                </h2>
                <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree"
                     data-bs-parent="#faqAccordion">
                    <div class="accordion-body">
                        Yes, we offer 24/7 customer support. You can reach us through live chat, email, or our help
                        center.
                    </div>
                </div>
            </div>
        </div>

    </div>

    <!-- /END THE FEATURETTES -->

    </div><!-- /.container -->

</main>