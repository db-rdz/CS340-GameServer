package Database;

public class MongoFactory implements IFactory {
	
	private MongoDAO mongoDAO; //TODO: change to dynamic for pass off

	@Override
	public void startTransaction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeTransaction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearExistingData() {
		// TODO Auto-generated method stub

	}
	
	public MongoDAO getMongoDAO() {
		return mongoDAO;
	}
	
	public void setMongoDAO(MongoDAO mongoDAO) {
		this.mongoDAO = mongoDAO;
	}

}
