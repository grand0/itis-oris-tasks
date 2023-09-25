<#include "base.ftl">

<#macro title>Main</#macro>

<#macro content>

    <#if username??>
        <#if error??>
            <p style="color: red">${error}</p>
        </#if>
        <form action="weather" method="get">
            <label>
                City
                <input type="text" name="city" required />
            </label>
            <input type="submit" value="Get weather" />
        </form>
    <#else>
        <p><b>Login</b> to get access to current weather description for any city.</p>
    </#if>

</#macro>
