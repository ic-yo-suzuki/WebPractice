<%@tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="type" required="true" %>


<%
if ("mona".equals(type) == true) {
%>
　　 ∧＿∧　　／￣￣￣￣￣<br />
　　（　´∀｀）＜　オマエモナー<br />
　　（　　　　） 　＼＿＿＿＿＿<br />
　　｜ ｜　|<br />
　　（_＿）＿）<br />
<%
} else if ("iruka".equals(type) == true) {
%>
|　何について調べますか？<br />
| ┌────────────┐<br />
|　|　お前を消す方法　　　　　　 　｜<br />
|　| 　　　　　　　　　 　　　  　 　　　　｜<br />
| └────────────┘<br />
|　[ オプション(O) ]　　　[ 検索(S) ]<br />
|<br />
｀──────────┐ ┌───<br />
　　　　　　　　　　　, '´l,　　..| ./<br />
　　　　　　　, -─-'- 、i_　　|/<br />
　　　 ＿_, '´　　　　　　　ヽ、<br />
　　　',ー--　●　　　　　　　ヽ、<br />
　　　 ｀"'ゝ、_　　　　　　　　　 ',<br />
　　　　　　〈｀'ｰ;＝=ヽ、〈ｰ- 、 !<br />
　　　　　　 ｀ｰ´　　　　ヽi｀ヽ iﾉ<br />
　　　　　　　　　　　　　　　 ! /<br />
　　　　　　　　　　　　　　r'´、ヽ<br />
　　　　　　　　　　　　　　｀´ヽノ<br />
<%
} else if ("exeneko".equals(type) == true) {
%>
　　　 　 　 ∧∧　 ／￣￣￣￣￣￣<br />
～′￣￣( exe)＜　エグって呼んで～<br />
　ＵＵ￣ＵＵ　　　 ＼＿＿＿＿＿＿<br />
<%
} else {
	throw new IllegalArgumentException("type =[" + type + "]");
}
%>
