<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
    <link rel="stylesheet" href="/css/util.css">
    <link rel="stylesheet" href="/css/adminBus.css">
    <link rel="stylesheet" href="/css/adminAddBus.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <!-- <script src="./js/welcomepage.js"></script> -->

    <title>BMS ADMIN | DASHBOARD</title>
</head>
<!-- NAVAR AND SIDEBAR -->

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
    <!-- Navigation-->
    <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
        <a class="navbar-brand" href="index.html">Bus Management System </a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
            data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
            aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav navbar-sidenav " id="exampleAccordion">
                <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Dashboard">
                    <a class="nav-link" href="AdminDashboard.html">
                        <i class="fa fa-fw fa-sign-in-alt"></i>
                        <span class="nav-link-text">Dashboard</span>
                    </a>
                </li>
                <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
                    <a class="nav-link" href="AdminBusInformation.html">
                        <i class="fa fa-fw fa-table"></i>
                        <span class="nav-link-text">Display Bus Information</span>
                    </a>
                <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Tables">
                    <a class="nav-link" href="AdminAddBus.html">
                        <i class="fa fa-fw fa-wrench"></i>
                        <span class="nav-link-text">Add New Bus</span>
                    </a>
                </li>
                </li>
                <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Example Pages">
                    <a class="nav-link" href="#collapseExamplePages" data-parent="#exampleAccordion">
                        <i class="fa fa-fw fa-file"></i>
                        <span class="nav-link-text">Generate Revenue Report</span>
                    </a>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto">

                <li class="nav-item">
                    <a class="nav-link" data-toggle="modal" data-target="#exampleModal">
                        <i class="fa fa-fw fa-sign-out"></i>Logout</a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="content-wrapper">
        <div class="container-fluid">
            <!-- Breadcrumbs-->
            <ol class="breadcrumb">
                <li class="breadcrumb-item">
                    <a href="#">Dashboard</a>
                </li>
                <li class="breadcrumb-item active">Add New Bus</li>
            </ol>

            <!-- Example DataTables Card-->
            <div class="card route-form-card mx-auto my-auto">
                <img class="card-img-top" src="/images/bus_form_img.jpg" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title font-weight-bold">Add a new Bus</h5>
                    <p class="card-text">
                    <form action="/admin/saveBus" method="post">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <div class="input-group mb-3 my-auto search-element">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i
                                                class="fas fa-registered form-icon text-bms-primary"></i></span>
                                    </div>
                                    <input type="text" name="registrationNo" class="form-control" placeholder="Registration Number"
                                        aria-describedby="basic-addon1">
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <div class="input-group mb-3 my-auto search-element">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i
                                                class="fas fa-bus form-icon text-bms-primary"></i></span>
                                    </div>
                                    <input type="text" name="busName" class="form-control" placeholder="Bus Name"
                                        aria-describedby="basic-addon1">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <div class="input-group mb-3 my-auto search-element">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i
                                                class="fas fa-chair form-icon text-bms-primary"></i></span>
                                    </div>
                                    <input type="number" name="seatCount" class="form-control" placeholder="Totals seats"
                                        aria-describedby="basic-addon1">
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <div class="input-group mb-3 my-auto search-element">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i
                                                class="fas fa-clock form-icon text-bms-primary"></i></span>
                                    </div>
                                    <input type="time" name="startTimeForm" class="form-control" placeholder="Departure time"
                                        aria-describedby="basic-addon1">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <div class="input-group mb-3 my-auto search-element">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i
                                                class="fas fa-rupee-sign form-icon text-bms-primary"></i></span>
                                    </div>
                                    <input type="number" name="fare" class="form-control" placeholder="Fare per km"
                                        aria-describedby="basic-addon1">
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <div class="input-group mb-3 my-auto search-element">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i
                                                class="fas fa-road form-icon text-bms-primary"></i></span>
                                    </div>
                                    <select class="form-select form-control" aria-label="Default select example"
                                        name="routeCode" required>
                                        <option value="">Select Route</option>
                                        <option value="1">1-Kolkata-Delhi</option>
                                        <option value="2">2-Delhi-Kolkata</option>
                                        <option value="3">3-Kolkata-Mumbai</option>
                                        <option value="4">4-Mumbai-Kolkata</option>
                                        <option value="5">5-Mumbai-Delhi</option>
                                        <option value="6">6-Delhi-Mumbai</option>

                                        <!-- Use forEach loop from JSTL to populate these options.   -->

                                        <!-- <c:forEach var="route" items="${routes}">
                                                <option value="${routeode}-${startName}-${lastName}">${startName}</option>
                                            </c:forEach> -->
                                    </select>
                                </div>
                            </div>

                        </div>
                        <div class="form-group">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="facilities" id="inlineRadio1"
                                    value="AC">
                                <label class="form-check-label" for="inlineRadio1"><span
                                        class="badge badge-success">AC</span></label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="facilities" id="inlineRadio2"
                                    value="NONAC">
                                <label class="form-check-label" for="inlineRadio2"><span class="badge badge-primary">NON
                                        AC</span></label>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-add">ADD BUS</button>
                    </form>

                    </p>
                </div>
            </div>

            <footer class="sticky-footer">
                <div class="container">
                    <div class="text-center">
                        <small>Copyright © Bus Management System 2022</small>
                    </div>
                </div>
            </footer>
            <!-- Scroll to Top Button-->
            <a class="scroll-to-top rounded" href="#page-top">
                <i class="fa fa-angle-up"></i>
            </a>
            <!-- Logout Modal-->
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div class="modal-body">Select "Logout" below if you are ready to end your current session.
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                            <a class="btn btn-primary" href="login.html">Logout</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
</body>


</html>