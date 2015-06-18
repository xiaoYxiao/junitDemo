<#import "lib/Common.ftl" as com>
<#escape x as x?html>

<@com.page title="Index">
  <a href="CityEdit!get.do">Add</a> | <a href="help.html">How this works?</a>
  
  <#if cityList?size = 0>
    <p>No messages.
  <#else>
    <p>The messages are:
    <table border=0 cellspacing=2 cellpadding=2 width="100%">
      <tr align=center valign=top>
        <th bgcolor="#C0C0C0">#</th>
        <th bgcolor="#C0C0C0">Id</th>
        <th bgcolor="#C0C0C0">Name</th>
        <th bgcolor="#C0C0C0">Message</th>
        <th bgcolor="#C0C0C0"></th>
      <#list cityList as e>
        <tr align=left valign=top>
          <td bgcolor="#E0E0E0">${e_index+1}</td>
          <td bgcolor="#E0E0E0">${e.id}</td>
          <td bgcolor="#E0E0E0"><a href="CityEdit!get.do?id=${e.id}">${e.name}</a></td>
          <td bgcolor="#E0E0E0">${e.description!""}</td>
          <td bgcolor="#E0E0E0"><a href="City!delete.do?cityIds=${e.id}">Delete</a></td>
      </#list>
    </table>
  </#if>
</@com.page>
  <p><a href="index.jsp">Back to the index page</a>

</#escape>