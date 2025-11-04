#!/usr/bin/env python3
"""
Generate a JaCoCo coverage badge in Shields.io style (SVG).
"""

import xml.etree.ElementTree as ET

xml_file = "target/site/jacoco/jacoco.xml"
badge_file = "images/coverage-badge.svg"

# Parse XML
tree = ET.parse(xml_file)
root = tree.getroot()

# Get total line coverage (last counter)
counter = root.findall(".//counter[@type='LINE']")[-1]
covered = int(counter.attrib["covered"])
missed = int(counter.attrib["missed"])
coverage_percent = int((covered / (covered + missed)) * 100)

# Choose color
color = "green" if coverage_percent > 80 else "yellow" if coverage_percent > 50 else "red"

# --- Layout settings ---
label = "coverage"
value = f"{coverage_percent}%"

# Rough text width estimation (Verdana 11px)
def text_width(s): return len(s) * 6

label_w = text_width(label) + 10
value_w = text_width(value) + 10
total_w = label_w + value_w
height = 20

# SVG template
svg = f"""<svg xmlns="http://www.w3.org/2000/svg" width="{total_w}" height="{height}" role="img" aria-label="{label}: {value}">
  <title>{label}: {value}</title>
  <linearGradient id="smooth" x2="0" y2="100%">
    <stop offset="0" stop-color="#bbb" stop-opacity=".1"/>
    <stop offset="1" stop-opacity=".1"/>
  </linearGradient>

  <!-- Left (label) -->
  <rect rx="3" width="{label_w}" height="{height}" fill="#555"/>
  <!-- Right (value) -->
  <rect rx="3" x="{label_w}" width="{value_w}" height="{height}" fill="{color}"/>
  <rect rx="3" width="{total_w}" height="{height}" fill="url(#smooth)"/>

  <!-- Text -->
  <g fill="#fff" text-anchor="middle" font-family="Verdana" font-size="11">
    <text x="{label_w/2}" y="14">{label}</text>
    <text x="{label_w + value_w/2}" y="14">{value}</text>
  </g>
</svg>"""

# Save SVG badge
with open(badge_file, "w") as f:
    f.write(svg)

print(f"✅ Coverage badge generated: {coverage_percent}% → {badge_file}")
