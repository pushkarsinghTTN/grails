<div class="col-lg-12" style="margin-top: 25px;">
    <div class=" panel panel-default ">
        <div class="panel-heading  " style="background:#007efc">
            <p>

            <h3 style="color:white">Registration</h3></p>
        </div>

        <div class="panel-body ">
            <g:form controller="login" action="register" method="post">
                <div class="form-group col-lg-10">
                    <label>First Name</label>
                    <input class="form-control" type="text" name="firstname"
                           placeholder="Enter Your First name">
                </div>

                <div class="col-lg-2"></div>

                <div class="form-group col-lg-10">
                    <label>Last name</label>
                    <input class="form-control" type="text" name="lastname" placeholder="Enter Your Last name">

                </div>

                <div class="form-group col-lg-10">
                    <label>Email</label>
                    <input class="form-control" type="email" name="email" placeholder="Enter Your Email">

                </div>

                <div class="form-group col-lg-10">
                    <label>Username</label>
                    <input class="form-control" type="text" name="username" placeholder="Enter Your Username">

                </div>

                <div class="form-group col-lg-10">
                    <label>Password</label>
                    <input class="form-control" type="password" name="password" placeholder="Enter Your Password">

                </div>

                <div class="form-group col-lg-10">
                    <label>Confirm Password</label>
                    <input class="form-control" type="password" name="confirmpassword"
                           placeholder="Confirm Your Password">

                </div>

                <div class="form-group col-lg-10">
                    <label>Photo</label>
                    <input class="form-control" type="file" name="photo">

                </div>

                <div class="form-group col-lg-10">
                    <input type="submit" class="form-control btn-primary" style="background:#007efc">
                </div>

            </g:form>

        </div>
    </div>

</div>