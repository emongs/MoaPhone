package controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.board.CommandAction;


/**
 * Servlet implementation class ControllerBoard
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Map<String, CommandAction> commandHandlerMap = new HashMap<>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException {
    	System.out.println("init");
    	String configFile = getInitParameter("configFile");
    	Properties prop = new Properties();
    	String configFielPath = getServletContext().getRealPath(configFile);
    	try (FileReader fis = new FileReader(configFielPath)) {
    		prop.load(fis);
    	} catch (IOException e) {
    		throw new ServletException(e);
    	}
    	
    	Iterator keyIter = prop.keySet().iterator();
    	while(keyIter.hasNext()) {
    		String command = (String) keyIter.next();
    		String handlerClassName = prop.getProperty(command);
    		
    		try{
    			Class<?> handlerClass = Class.forName(handlerClassName);
    			CommandAction handlerInstance = (CommandAction) handlerClass.newInstance();
    			commandHandlerMap.put(command,handlerInstance);
    		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
    			throw new ServletException(e);
    		}
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request,response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("********************process");
		String command = request.getRequestURI();
		System.out.println(command);
		if(command.indexOf(request.getContextPath()) == 0) {
			command = command.substring(request.getContextPath().length());
		}
			System.out.println(command);
			CommandAction handler = commandHandlerMap.get(command);
			String viewPage = null;
			
			try {
				viewPage = handler.requestPro(request, response);
			} catch (Throwable e) {
				throw new ServletException(e);
			}
			
			if(viewPage != null) {
				RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
				dispatcher.forward(request, response);
		}
		
	}
	
}
