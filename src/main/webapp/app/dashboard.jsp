<%@ include file="../dashboardHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="m-4 p-4 width-medium">
    <div class="dashboard-header m-4">
        <div class="dashboard-menu">
            <div class="menu-item border-dashed">
                <a href="/app/recipe/add">
                    <i class="far fa-plus-square icon-plus-square"></i>
                    <span class="title">dodaj przepis</span>
                </a>
            </div>
            <div class="menu-item border-dashed">
                <a href="/app/plan/add">
                    <i class="far fa-plus-square icon-plus-square"></i>
                    <span class="title">dodaj plan</span>
                </a>
            </div>
            <div class="menu-item border-dashed">
                <a href="/app/recipe/plan/add">
                    <i class="far fa-plus-square icon-plus-square"></i>
                    <span class="title">dodaj przepis do planu</span>
                </a>
            </div>
        </div>

        <div class="dashboard-alerts">
            <div class="alert-item alert-info">
                <i class="fas icon-circle fa-info-circle"></i>
                <span class="font-weight-bold">Liczba przepisów: ${recipeCount}</span>
            </div>
            <div class="alert-item alert-light">
                <i class="far icon-calendar fa-calendar-alt"></i>
                <span class="font-weight-bold">Liczba planów: ${planCount}</span>
            </div>
        </div>
    </div>
    <div class="m-4 p-4 border-dashed">

        <h2 class="dashboard-content-title">
            <span>Ostatnio dodany plan:</span> ${plan}
        </h2>
        <table class="table">
            <c:forEach items="${recipePlansMap2}" var="entry" varStatus="i">
                <thead>
                <tr class="d-flex">
                    <th class="col-2">${entry.key}</th>
                    <th class="col-8"></th>
                    <th class="col-2"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${entry.value}" var="recipePlan" varStatus="i">
                    <tr class="d-flex">
                        <td class="col-2">${recipePlan.mealName}</td>
                        <td class="col-8">${recipePlan.recipeName}</td>
                        <td class="col-2">
                            <form action="/app/dashboardServlet" method="post">
                                <input class="btn btn-primary rounded-0" type="submit" value="Szczegóły">
                                <input type="hidden" name="recipeId" value="${recipePlan.getRecipeName()}">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </c:forEach>
        </table>
    </div>
</div>
</div>
</section>
