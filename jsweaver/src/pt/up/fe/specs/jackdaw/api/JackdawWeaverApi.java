package pt.up.fe.specs.jackdaw.api;

import org.lara.interpreter.weaver.utils.LaraResourceProvider;

public enum JackdawWeaverApi implements LaraResourceProvider {
	
	JACKDAW_CLASS_JP("jp/JackdawClassJp.lara"),
    JACKDAW_JOIN_POINT("jp/JackdawJoinPoint.lara"),
    COMMON_JOIN_POINTS("jp/CommonJoinPoints.lara"),
	JOINPOINTS("JoinPoints.lara");

	private final String resource;

	private static final String WEAVER_PACKAGE = "jackdaw/";
	private static final String BASE_PACKAGE = "weaver/";

	/**
	 * @param resource
	 */
	private JackdawWeaverApi(String resource) {
		this.resource = WEAVER_PACKAGE + getSeparatorChar() + BASE_PACKAGE + resource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.suikasoft.SharedLibrary.Interfaces.ResourceProvider#getResource()
	 */
	@Override
	public String getOriginalResource() {
		return resource;
	}
}
