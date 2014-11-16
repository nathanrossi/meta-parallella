SUMMARY = "An image that provides a complete build environment for working with the Epiphany co-processor"
LICENSE = "MIT"

inherit core-image

IMAGE_FEATURES += "ssh-server-openssh"

IMAGE_INSTALL = " \
		packagegroup-core-boot \
		${CORE_IMAGE_EXTRA_INSTALL} \
		packagegroup-core-buildessential \
		packagegroup-epiphany-buildessential \
		packagegroup-epiphany-tools \
		git \
		"

