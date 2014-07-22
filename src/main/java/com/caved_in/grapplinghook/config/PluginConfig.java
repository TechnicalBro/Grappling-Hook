package com.caved_in.grapplinghook.config;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "config")
public class PluginConfig {

	@Element(name = "teleport-hooked")
	private boolean teleportHooked = false;

	@Element(name = "fall-damage")
	private boolean fallDamage = false;

	@Element(name = "enable-cooldown")
	private boolean cooldown = false;

	@Element(name = "cooldown-time-seconds")
	private int cooldownSeconds = 0;

	public PluginConfig(@Element(name = "teleport-hooked")boolean teleportHooked, @Element(name = "fall-damage")boolean fallDamage, @Element(name = "enable-cooldown")boolean cooldown, @Element(name = "cooldown-time-seconds")int cooldownSeconds) {
		this.teleportHooked = teleportHooked;
		this.fallDamage = fallDamage;
		this.cooldown = cooldown;
		this.cooldownSeconds = cooldownSeconds;
	}

	public PluginConfig() {

	}

	public boolean isTeleportHooked() {
		return teleportHooked;
	}

	public boolean isFallDamage() {
		return fallDamage;
	}

	public boolean isCooldown() {
		return cooldown;
	}

	public int getCooldownSeconds() {
		return cooldownSeconds;
	}
}
