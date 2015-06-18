<#import "lib/Common.ftl" as com>

<@com.page title="Add Entry">
  <#if errors?size != 0>
    <p><font color=red>Please correct the following problems:</font>
    <ul>
      <#list errors as e>
        <li><font color=red>${e}</font>
      </#list>
    </ul>
  </#if>
  
  <form method="POST" action="CityEdit!save.do">
    <p>Id:<br>
    <input type="text" name="city.id" value="${city.id!''}" size=60>
    <p>Name:<br>
    <input type="text" name="city.name" value="${city.name!''}" size=60>
    <p>Message:<br>
    <textarea name="city.description" wrap="soft" rows=3 cols=60>${city.description!''}</textarea>
    <p><input type="submit" value="Submit">
  </form>
  <p><a href="City!list.do">Back to the list page</a>
</@com.page>

