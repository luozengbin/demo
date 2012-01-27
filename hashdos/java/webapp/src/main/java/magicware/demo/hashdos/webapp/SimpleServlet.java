package magicware.demo.hashdos.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleServlet extends HttpServlet {

	private static final long serialVersionUID = 6203983675733264397L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		
		if ("html".equals(request.getParameter("format"))) {
			writeHtml(request, out);
		} else {
			writeText(request, out);
		}

		out.close();
	}

	private void writeText(HttpServletRequest request, PrintWriter out) {
		
		//開始時刻
		long start = System.currentTimeMillis();

		System.out.print(String.format(">>> SimpleServlet start process at %s \n", (new Date(start)).toString()));
		
		//リクエストパラメータの取得
		System.out.println("get request parameter ");
		Map<String, String[]> parameters = request.getParameterMap();
		System.out.print(parameters.getClass());
		
		
		//リクエストパラメータの参照
		System.out.println("reading hashmap");
		boolean printDetail = parameters.containsKey("detail");
		
		if(printDetail){
			out.print("+--------------------------------------------+--------------------+\n");
			out.print("|  key                                       |  value             |\n");
			out.print("+--------------------------------------------+--------------------+\n");
		}
		
		for (String key : parameters.keySet()) {
			
			//Hashmapから値を走査
			Object values = parameters.get(key);
			
			if(printDetail){
				out.print(String.format("|  %-40s  |  %-15s  |\n", key, key.hashCode()));
				out.print("+--------------------------------------------+--------------------+\n");
				out.flush();
			}
			
			System.out.print(".");
		}
		
		System.out.print("\n");
		
		//終了時刻
		long end = System.currentTimeMillis();
		System.out.print(String.format("<<< SimpleServlet stop process at %s \n", (new Date(end)).toString()));
		
		//レポートの作成
		out.print("+---------------+--------------------------------+\n");
		out.print("|  Start Time   |  " + String.format("%-28s", (new Date(start)).toString()) + "  |\n");
		out.print("+---------------+--------------------------------+\n");
		out.print("|  End   Time   |  " + String.format("%-28s", (new Date(end)).toString()) + "  |\n");
		out.print("+---------------+--------------------------------+\n");
		out.print("|  Cost  Time   |  " + String.format("%-28s", (end - start) / 1000.0) + "  |\n");
		out.print("+---------------+--------------------------------+\n");
	}

	private void writeHtml(HttpServletRequest request, PrintWriter out) {
		
		out.print("<h3>SimpleServlet <font color='red'>start</font> process at <font color='bule'>"
				+ Calendar.getInstance().getTime().toString() + "</font></h3>");

		out.print("<table border='1'>");
		out.print("<tr><th>key</th><th>value</th></tr>");

		Map<String, String[]> parameters = request.getParameterMap();
		for (String key : parameters.keySet()) {
			out.print("<tr>");
			out.print("<td>" + key + "</td>");
			out.print("<td>" +  Arrays.asList(parameters.get(key)).toString() + "</td>");
			out.print("</tr>");
		}
		out.print("</table>");

		out.print("<h3>SimpleServlet <font color='red'>stop</font> process at <font color='bule'>"
				+ Calendar.getInstance().getTime().toString() + "</font></h3>");
		
		out.flush();
	}
}