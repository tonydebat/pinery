package ca.on.oicr.pinery.service;

import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;

import ca.on.oicr.pinery.api.AttributeName;
import ca.on.oicr.pinery.api.ChangeLog;
import ca.on.oicr.pinery.api.Sample;
import ca.on.oicr.pinery.api.SampleProject;
import ca.on.oicr.pinery.api.Type;

public interface SampleService {

	public List<Sample> getSamples(Boolean archived, Set<String> projects, Set<String> types, DateTime before,
			DateTime after);

	public Sample getSample(Integer id);

	public List<SampleProject> getSampleProjects();
	
	public List<Type> getTypes();
	
	public List<AttributeName> getAttributeNames();
	
	public List<ChangeLog> getChangeLogs();
	
	public ChangeLog getChangeLog(Integer id);

}
