f = [
    ('A',0),
    ('ZZZZZZZZZZZZZZZZZZZZZZZZZ',0),
    ('AB', 0),
    ('ZZZZZZZZZZZZZZZZZZZZZZZY', 0),
    ('PROJECT',0),
    ('0',0),
    ('1234567890123456789012345',0),
    ('01',0),
    ('123456789012345678901234',0),
    ('125845',0),
    ('PROJECT125845',0),
    ('A',1000000000),
    ('ZZZZZZZZZZZZZZZZZZZZZZZZZ',1000000000),
    ('AB',1000000000),
    ('ZZZZZZZZZZZZZZZZZZZZZZZZY', 1000000000),
    ('PROJECT',1000000000),
    ('125845',1000000000),
    ('PROJECT125845',1000000000),
    ('PROJECT',1),
    ('125845',1),
    ('PROJECT125845',1),
    ('PROJECT', 999999999),
    ('125845',999999999),
    ('PROJECT125845',999999999),
    ('AB',1),
    ('12',1),
    ('ZZZZZZZZZZZZZZZZZZZZZZZY',999999999),
    ('123456789012345678901234', 999999999),
    ('AB',999999999),
    ('12',999999999),
    ('ZZZZZZZZZZZZZZZZZZZZZZZY', 1),
    ('123456789012345678901234', 1)]
s = ''
for (name, cost) in f:
    # s += '@Test public void testCreateActivity_' + str(name) + '_' + str(cost) + '() throws Exception { Activity activityA = new Activity("' + str(name) + '", "description", 2, 2, 2, 2, ' + str(cost) + 'd, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), 1, 1, null); activityA.setProject(project); activityA.setMember(member); int insertedActivityId = activity_model.insertNewActivity(activityA); assertTrue(insertedActivityId != 0); Activity activityB = activity_model.getActivityById(insertedActivityId); assertEquals("' + str(name) + '", activityB.getActivityLabel()); assertEquals(new Double(' + str(cost) + '), new Double(activityB.getPlannedCost())); if (insertedActivityId != 0) { activity_model.deleteActivityById(insertedActivityId); }}'
    # s += '@Test public void testUpdateActivity_' + str(name) + '_' + str(cost) + '() throws Exception { Activity activityA = new Activity("name", "description", 2, 2, 2, 2, 0d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), 1, 1, null); activityA.setProject(project); activityA.setMember(member); int insertedActivityId = activity_model.insertNewActivity(activityA); assertTrue(insertedActivityId != 0); activityA = activity_model.getActivityById(insertedActivityId); activityA.setProject(project); activityA.setMember(member); activityA.setActivityLabel("' + str(name) + '"); activityA.setCost(' + str(cost) + 'd); activity_model.updateActivity(activityA); Activity activityB = activity_model.getActivityById(insertedActivityId); assertEquals("' + str(name) + '", activityB.getActivityLabel()); assertEquals(new Double(' + str(cost) + '), new Double(activityB.getPlannedCost())); if (insertedActivityId != 0) { activity_model.deleteActivityById(insertedActivityId); }}'
    # s += '@Test public void testCreateProject_' + str(name) + '_' + str(cost) + '() throws Exception { Project projectA = new Project("' + str(name) + '", ' + str(cost)  + 'd, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), "description"); int insertedProjectID = project_model.insertNewProject(projectA); assertTrue(insertedProjectID  != Database.ERROR); Project projectB = project_model.getProjectById(insertedProjectID); assertEquals("' + str(name) + '", projectB.getProjectName()); assertEquals(new Double(' + str(cost) + 'd), new Double(projectB.getBudget())); if (insertedProjectID != Database.ERROR) { project_model.deleteProjectById(insertedProjectID); }}'
    s += '@Test public void testUpdateProject_' + str(name) + '_' + str(cost) + '() throws Exception { Project projectA = new Project("name", 0.0d, DateHelper.parse("11/11/2011", Config.DATE_FORMAT_SHORT), DateHelper.parse("11/12/2011", Config.DATE_FORMAT_SHORT), "description"); int insertedProjectID = project_model.insertNewProject(projectA); assertTrue(insertedProjectID  != Database.ERROR); projectA = project_model.getProjectById(insertedProjectID); projectA.setProjectName("' + str(name) + '"); projectA.setBudget(' + str(cost) + 'd); project_model.updateProject(projectA); Project projectB = project_model.getProjectById(insertedProjectID); assertEquals("' + str(name) + '", projectB.getProjectName()); assertEquals(new Double(' + str(cost) + 'd), new Double(projectB.getBudget())); if (insertedProjectID != Database.ERROR) { project_model.deleteProjectById(insertedProjectID); }}'
print s
