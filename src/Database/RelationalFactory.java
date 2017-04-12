package Database;

public class RelationalFactory implements IFactory{
	
	private RelationalDAO relationalDAO;

	@Override
	public void closeTransaction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearExistingData() {
		// TODO Auto-generated method stub
		
	}
	
	public RelationalDAO getRelationalDAO() {
		return relationalDAO;
	}
	public void setRelationalDAO(RelationalDAO relationalDAO) {
		this.relationalDAO = relationalDAO;
	}

}
