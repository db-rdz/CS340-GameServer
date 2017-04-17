package Database;

public class RelationalFactory implements IFactory{
	
	private RelationalDAO relationalDAO; //TODO: change to dynamic for pass off

	@Override
	public void startTransaction() {
		// TODO Use class loader
		
	}
	
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
