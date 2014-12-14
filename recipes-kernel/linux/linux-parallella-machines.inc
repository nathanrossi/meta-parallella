
# Leave the KMACHINE as zynq so that zynq common kernel config is picked up
COMPATIBLE_MACHINE_parallella = "parallella"
KMACHINE_parallella ?= "zynq"

COMPATIBLE_MACHINE_parallella1-hdmi = "parallella1-hdmi"
KMACHINE_parallella1-hdmi ?= "zynq"

# Default kernel config fragements for specific machines

# KERNEL_FEATURES_append_parallella += 
# KERNEL_FEATURES_append_parallella1-hdmi += 

# SRC_URI_append_parallella +=
SRC_URI_append_parallella1-hdmi += " file://hdmikernelConfigAdditions.cfg \
                                   "