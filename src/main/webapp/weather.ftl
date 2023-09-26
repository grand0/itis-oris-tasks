<#include "base.ftl">

<#macro title>Weather in ${weather.city}</#macro>

<#macro content>
    <h3>Weather in ${weather.city}</h3>
    <b>Temperature: </b>${weather.temp}°C<br>
    <b>Humidity: </b>${weather.humidity}%<br>
    <b>Weather description: </b>${weather.description}
</#macro>
