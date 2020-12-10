
<%@ include file="../dashboardHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="dashboard-content border-dashed p-3 m-4 view-height">
    <div class="row border-bottom border-3 p-1 m-1">
        <div class="col noPadding"><h3 class="color-header text-uppercase">Czy chcesz usunąć ten przepis?</h3></div>
    </div>
    <table class="table border-bottom schedules-content">
            <a href="/app/recipe/delete/now?id=${id}" class="btn btn-success rounded-0 text-light m-1">TAK</a>
            <a href="/app/recipe/list" class="btn btn-danger rounded-0 text-light m-1">NIE</a>
    </table>
</div>
</section>