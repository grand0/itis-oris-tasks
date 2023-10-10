<#assign linkJquery=true>

<#include "base.ftl">

<#macro title>Main</#macro>

<#macro content>

    <script>
        $(document).on("click", "#submit-button", function () {
            let city = $("#city-field").val();
            $.get("/weather?city=" + city, function (response) {
                $("#weather-response").text(response);
            });
        });
    </script>

    <#if username??>
        <#if error??>
            <p style="color: red">${error}</p>
        </#if>
        <form>
            <label>
                City
                <input id="city-field" type="text" name="city" required />
            </label>
            <input id="submit-button" type="button" value="Get weather" />
        </form>
        <div id="weather-response"></div>
    <#else>
        <p><b>Login</b> to get access to current weather description for any city.</p>
    </#if>

</#macro>
