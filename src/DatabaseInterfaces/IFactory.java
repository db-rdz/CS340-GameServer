package DatabaseInterfaces;

public interface IFactory {

	public Boolean startTransaction();
	public void closeTransaction();
	public void clearExistingData();
	public iDAO2 getDAO();
}
