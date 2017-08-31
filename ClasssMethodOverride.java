
class Parent {

	public void first(int i) {
		Log.e("test", "Parent first");
	}
}


class Children extends Parent {

	@Override
	public void first(int i) {
		Log.e("test", "Children first");
	}

	public void second() {
		Log.e("test", "Children second");
	}

	public void third() {
		Log.e("test", "Children third");
	}

}

public static void main(String[] args) {					
					
    String method_name = "first";
    Children child = new Children();
    
    boolean ret = testCase(child, method_name);
    Log.e("test", "Children has overridden '" + method_name + "': " + ret);
					
					
    method_name = "second";
    ret = testCase(child, method_name);
    Log.e("test", "Children has overridden '" + method_name + "': " + ret);
					
					
    method_name = "third";
    ret = testCase(child, method_name);
    Log.e("test", "Children has overridden '" + method_name + "': " + ret);
}

	
public static boolean testCase(Object child, final String method_name) {
		
		Method[] cfs = child.getClass().getDeclaredMethods();
		Method[] pfs = child.getClass().getSuperclass().getDeclaredMethods();
		
		for (Method bm : cfs) {
			
			if (!bm.getName().equals(method_name)) {
				continue;
			}
			
			for (Method am : pfs) {
				
				if (!am.getName().equals(method_name)) {
					continue;
				}
								
				if ( /*bm.getName().equals(am.getName()) && */
						bm.getReturnType().equals(am.getReturnType())) {

					Class<?>[] bc = bm.getParameterTypes();
					Class<?>[] ac = am.getParameterTypes();

					if (bc.length == ac.length) {

						boolean isEqual = true;
						for (int i = 0; i < bc.length; i++) {
							if (!bc[i].equals(ac[i])) {
								isEqual = false;
								break;
							}
						}

						if (isEqual) {
							Log.e("test", " \"" + child.getClass().getSimpleName() + "\" override method: " + bm.getName() + " of \""
									+ child.getClass().getSuperclass().getSimpleName() + "\"");
							return true;
						}

					}
				}
			}
		}
		
    return false;	
}

