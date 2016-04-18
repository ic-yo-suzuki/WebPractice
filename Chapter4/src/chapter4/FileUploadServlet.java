package chapter4;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(value = "/file_upload")
@MultipartConfig(fileSizeThreshold = 50000, maxFileSize = 50000, location = "C:\\tmp")

public class FileUploadServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		InputStream inputStream = null;
		try{
			Part part = request.getPart("content");
			PrintWriter out = response.getWriter();
			String fileName = printPartInfo(part);

			inputStream = part.getInputStream();
			int readData;
			while((readData = inputStream.read()) != -1){
				out.write(readData);
			}
			part.write(fileName);
			out.flush();
		}finally{
			if(inputStream != null){
				inputStream.close();
			}
		}
	}
	private String printPartInfo(Part part){
		System.out.println("==Header==");
		for(String headerName : part.getHeaderNames()){
			System.out.println(headerName + " = " + part.getHeader(headerName) + ", ");
		}
		System.out.println("==Header==");
		String fileName = getFileName(part);

		System.out.println("FileName : " + fileName);
		System.out.println("Name : " + part.getName());
		System.out.println("ContentType : " + part.getContentType());
		System.out.println("Size : " + part.getSize() + "バイト");

		return fileName;
	}

	private String getFileName(Part p){
		String contentDisposition = p.getHeader("Content-Disposition");
		String[] contentDispositions = contentDisposition.split(";");

		for(String cd : contentDispositions){


			if(cd.trim().startsWith("filename")){
				String filePath = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				int lastSeparatorIndex = filePath.lastIndexOf(File.separator);
				return filePath.substring(lastSeparatorIndex + 1);
			}

		}

		throw new IllegalStateException();
	}
}
