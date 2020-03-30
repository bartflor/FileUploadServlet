package pl.bartflor.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;

public class FileDao {
	private DatabaseAcces dbAcces;
	private DaoFactory daoFactory;
	private ServletContext sContext;

	public FileDao(ServletContext sContext) {
		this.sContext = sContext;
		this.daoFactory = new DaoFactory(sContext);
		this.dbAcces = daoFactory.createDaoObject();
	}

	public boolean setNewUpload(FileItem item, String owner, String path) {
		dbAcces = daoFactory.createDaoObject();
		String sqlInsert = "INSERT INTO files_tbl(file_name, type, size, upload_date, owner, path) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		dbAcces.executeStatement(sqlInsert, item.getName(), item.getContentType(), "" + item.getSize(),
				LocalDateTime.now().toString(), owner, path);
		return true;
	}

	public List<UploadFile> getFilesList(HttpSession userSession) {
		List<UploadFile> fileList = new LinkedList<>();
		User user = (User) userSession.getAttribute("user");
		String sqlQuerry = "SELECT file_id, file_name, type, size, upload_date FROM files_tbl WHERE owner=?;";
		ResultSet result = dbAcces.getQuerryResult(sqlQuerry, user.getName());
		try {
			while (result.next()) {
				UploadFile file = new UploadFile(result.getString("file_id"), result.getString("file_name"),
						result.getString("type"), result.getString("size"), result.getString("upload_date"));
				fileList.add(file);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			dbAcces.close();
		}
		return fileList;
	}

	public String getFilePath(String fileId, HttpSession userSession) {
		User user = (User) userSession.getAttribute("user");
		String sqlQuerry = "SELECT path FROM files_tbl WHERE owner=? AND file_id=?;";
		ResultSet result = dbAcces.getQuerryResult(sqlQuerry, user.getName(), fileId);
		try {
			while (result.next()) {
				return result.getString("path");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		} finally {
			dbAcces.close();
		}
		return null;
	}

	public boolean deleteFile(String fileId, HttpSession userSession) {
		User user = (User) userSession.getAttribute("user");
		String sqlQuerry = "DELETE FROM files_tbl WHERE owner=? AND file_id=?;";
		return dbAcces.executeStatement(sqlQuerry, user.getName(), fileId);

	}

	public double getFilesSize(HttpSession userSession) {
		User user = (User) userSession.getAttribute("user");
		String sqlQuerry = "SELECT SUM(size) FROM files_tbl WHERE owner=?;";
		ResultSet result = dbAcces.getQuerryResult(sqlQuerry, user.getName());
		try {
			while (result.next()) {
				String totalFilesSize = result.getString("SUM(size)");
				if (totalFilesSize != null) {
					return Double.parseDouble(result.getString("SUM(size)"));
				} else {
					return 0.0;
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			dbAcces.close();
		}
		return 0;
	}

	public StorageInfo getStorageInfo(HttpSession userSession) {
		int totalStorage = Integer.parseInt(sContext.getInitParameter("totalStorageSpace"));
		int filesSize = (int) getFilesSize(userSession);
		int spaceLeft = totalStorage - filesSize;
		return new StorageInfo(totalStorage, filesSize, spaceLeft);
	}

}
