
<%@ include file="../dashboardHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
            <div class="m-4 p-3 width-medium">
                <div class="dashboard-content border-dashed p-3 m-4 view-height">
                    <!-- fix action, method -->
                    <!-- add name attribute for all inputs -->
                    <form method="post">
                        <input type="hidden" name="id" value="${plan.id}"/>
                    <div class="row border-bottom border-3 p-1 m-1">
                        <div class="col noPadding">
                            <h3 class="color-header text-uppercase">NOWY PLAN</h3>
                        </div>
                        <div class="col d-flex justify-content-end mb-2">
                            <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz</button>
                        </div>
                    </div>

                    <div class="schedules-content" >
                            <div class="form-group row">
                                <label for="planName" class="col-sm-2 label-size col-form-label">
                                    Nazwa planu
                                </label>
                                <div class="col-sm-10">
                                    <input class="form-control" name="namePlan" value="${planEdit.name}" id="planName" placeholder="Nazwa planu">
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="planDescription" class="col-sm-2 label-size col-form-label">
                                    Opis planu
                                </label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" name="descriptionPlan" rows="5" id="planDescription" >${planEdit.description}</textarea>
                                </div>
                            </div>

                    </div>
                    </form>
                </div>
            </div>
        </div>
    </section>


    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>