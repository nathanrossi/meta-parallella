
# Leave the KMACHINE as zynq so that zynq common kernel config is picked up
COMPATIBLE_MACHINE_parallella = "parallella"
KMACHINE_parallella ?= "zynq"

COMPATIBLE_MACHINE_parallella-hdmi = "parallella-hdmi"
KMACHINE_parallella-hdmi ?= "zynq"

# Default kernel config fragements for specific machines

# KERNEL_FEATURES_append_parallella += 
# KERNEL_FEATURES_append_parallella-hdmi += 

# SRC_URI_append_parallella +=
SRC_URI_append_parallella-hdmi += " file://hdmikernelConfigAdditions.cfg \
                                   "