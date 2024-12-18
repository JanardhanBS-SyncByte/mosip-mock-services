package io.mosip.mock.sdk.service;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.env.Environment;

import io.mosip.kernel.biometrics.constant.BiometricFunction;
import io.mosip.kernel.biometrics.constant.BiometricType;
import io.mosip.kernel.biometrics.model.SDKInfo;

/**
 * Service class providing SDK information including API version, samples,
 * supported modalities, and supported biometric methods.
 */
public class SDKInfoService extends SDKService {
	private String apiVersion;
	private String sample1;
	private String sample2;
	private String sample3;

	/**
	 * Constructs an instance of {@code SDKInfoService} with the specified
	 * environment, API version, and sample information.
	 *
	 * @param env        The environment configuration
	 * @param apiVersion The version of the SDK API
	 * @param sample1    Sample information 1
	 * @param sample2    Sample information 2
	 * @param sample3    Sample information 3
	 */
	public SDKInfoService(Environment env, String apiVersion, String sample1, String sample2, String sample3) {
		super(env, null);
		this.apiVersion = apiVersion;
		this.sample1 = sample1;
		this.sample2 = sample2;
		this.sample3 = sample3;
	}

	/**
	 * Retrieves SDK information including API version, sample details, supported
	 * modalities, and supported biometric methods.
	 *
	 * @return SDKInfo object containing SDK information
	 */
	public SDKInfo getSDKInfo() {
		SDKInfo sdkInfo = new SDKInfo(this.apiVersion, this.sample1, this.sample2, this.sample3);
		List<BiometricType> supportedModalities = new ArrayList<>();
		supportedModalities.add(BiometricType.FINGER);
		supportedModalities.add(BiometricType.FACE);
		supportedModalities.add(BiometricType.IRIS);
		sdkInfo.setSupportedModalities(supportedModalities);
		Map<BiometricFunction, List<BiometricType>> supportedMethods = new EnumMap<>(BiometricFunction.class);
		supportedMethods.put(BiometricFunction.MATCH, supportedModalities);
		supportedMethods.put(BiometricFunction.QUALITY_CHECK, supportedModalities);
		supportedMethods.put(BiometricFunction.EXTRACT, supportedModalities);
		supportedMethods.put(BiometricFunction.CONVERT_FORMAT, supportedModalities);
		sdkInfo.setSupportedMethods(supportedMethods);
		return sdkInfo;
	}
}