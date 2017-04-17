package Database;

public interface IFactory {

	public void startTransaction();
	public void closeTransaction();
	public void clearExistingData();
}
