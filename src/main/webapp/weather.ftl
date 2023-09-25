<#include "base.ftl">

<#macro title>Weather in ${city}</#macro>

<#macro content>
    <h3>Weather in ${city}</h3>
    <b>Temperature: </b>${temp}°C<br>
    <b>Humidity: </b>${humidity}%<br>
    <b>Weather description: </b>${weatherDescription}
</#macro>
