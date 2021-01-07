USE [VendorPortal]
GO
SET IDENTITY_INSERT [sec].[Permission] ON 
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (1, N'UM', N'View', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (2, N'UM', N'Edit', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (3, N'UM', N'Create', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (4, N'UM', N'Delete', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (5, N'RM', N'View', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (6, N'RM', N'Edit', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (7, N'RM', N'Create', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (8, N'RM', N'Delete', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (9, N'S-AB', N'View', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (10, N'S-AB', N'Edit', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (11, N'S-INS', N'View', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (12, N'S-INS', N'Edit', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (13, N'S-INV', N'Create', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (14, N'S-INV', N'Approve', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (15, N'S-INV', N'V-View', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (16, N'S-INV', N'L-View', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (17, N'SL', N'View', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (18, N'SL', N'Inspect', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (19, N'SL', N'Invoice', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (20, N'SL', N'Approve', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (21, N'INS', N'View', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (22, N'INS', N'Inspect', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (23, N'INV', N'View', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (24, N'INV', N'Create', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (25, N'INV', N'Approve', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (26, N'SL-INV', N'V-View', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (27, N'SL-INV', N'L-View', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (28, N'Admin-Not', N'View', CAST(N'2020-10-30T17:11:15.783' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (29, N'Admin-Not', N'Create', CAST(N'2020-10-30T17:11:15.837' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (30, N'Admin-Not', N'Edit', CAST(N'2020-10-30T17:11:15.837' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (31, N'Admin-Not', N'Delete', CAST(N'2020-10-30T17:11:15.843' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (32, N'Admin-Rate', N'View', CAST(N'2020-10-30T17:11:15.853' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (33, N'Admin-Rate', N'Create', CAST(N'2020-10-30T17:11:15.857' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (34, N'Admin-Rate', N'Edit', CAST(N'2020-10-30T17:11:15.857' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (35, N'Admin-Rate', N'Delete', CAST(N'2020-10-30T17:11:15.860' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (36, N'Admin-Inspection', N'Edit', CAST(N'2020-10-30T17:11:15.863' AS DateTime), NULL)
GO
INSERT [sec].[Permission] ([PermissionID], [Domain], [Permission], [EffectiveStartDate], [EffectiveEndDate]) VALUES (37, N'Admin-WO', N'Submit', CAST(N'2020-10-30T17:11:15.873' AS DateTime), NULL)
GO
SET IDENTITY_INSERT [sec].[Permission] OFF
GO
SET IDENTITY_INSERT [sec].[RolePermission] ON 
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (276, 1, 1, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (277, 1, 2, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (278, 1, 3, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (279, 1, 4, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (280, 1, 5, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (281, 1, 6, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (282, 1, 7, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (283, 1, 8, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (284, 1, 9, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (285, 1, 10, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (286, 1, 11, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (287, 1, 12, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (288, 1, 13, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (289, 1, 14, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (290, 1, 15, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (291, 1, 16, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (292, 1, 17, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (293, 1, 18, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (294, 1, 19, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (295, 1, 20, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (296, 1, 21, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (297, 1, 22, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (298, 1, 23, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (299, 1, 24, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (300, 1, 25, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (301, 2, 1, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (302, 2, 2, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (303, 2, 3, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (304, 2, 4, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (305, 2, 5, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (306, 3, 9, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (307, 3, 11, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (308, 3, 15, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (309, 3, 16, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (310, 3, 17, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (311, 4, 17, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (312, 4, 20, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (313, 5, 9, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (314, 5, 11, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (315, 5, 15, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (316, 5, 16, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (317, 5, 14, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (318, 6, 11, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (319, 6, 12, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (320, 7, 17, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (321, 7, 18, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (322, 8, 15, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (323, 8, 16, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (324, 8, 17, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (325, 9, 9, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (326, 9, 10, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (327, 10, 15, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (328, 10, 13, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (329, 11, 17, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (330, 11, 19, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (331, 11, 26, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (332, 4, 27, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (333, 8, 27, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (334, 5, 23, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (335, 4, 23, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (336, 5, 25, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (338, 4, 26, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (339, 6, 9, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (340, 6, 21, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (343, 10, 23, CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (344, 12, 28, CAST(N'2020-10-30T17:12:36.400' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (345, 12, 29, CAST(N'2020-10-30T17:12:36.410' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (346, 12, 30, CAST(N'2020-10-30T17:12:36.417' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (347, 12, 31, CAST(N'2020-10-30T17:12:36.427' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (348, 12, 32, CAST(N'2020-10-30T17:12:36.433' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (349, 12, 33, CAST(N'2020-10-30T17:12:36.443' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (350, 12, 34, CAST(N'2020-10-30T17:12:36.447' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (351, 12, 35, CAST(N'2020-10-30T17:12:36.447' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (352, 12, 36, CAST(N'2020-10-30T17:12:36.460' AS DateTime), NULL)
GO
INSERT [sec].[RolePermission] ([RolePermissionID], [RoleID], [PermissionID], [EffectiveStartDate], [EffectiveEndDate]) VALUES (353, 12, 37, CAST(N'2020-10-30T17:12:36.467' AS DateTime), NULL)
GO
SET IDENTITY_INSERT [sec].[RolePermission] OFF
GO
SET IDENTITY_INSERT [sec].[Role] ON 
GO
INSERT [sec].[Role] ([RoleID], [RoleName], [EffectiveStartDate], [EffectiveEndDate]) VALUES (1, N'LCEC IT Support', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Role] ([RoleID], [RoleName], [EffectiveStartDate], [EffectiveEndDate]) VALUES (2, N'LCEC IT Provisioning', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Role] ([RoleID], [RoleName], [EffectiveStartDate], [EffectiveEndDate]) VALUES (3, N'LCEC Management', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Role] ([RoleID], [RoleName], [EffectiveStartDate], [EffectiveEndDate]) VALUES (4, N'LCEC Invoice Approver SL', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Role] ([RoleID], [RoleName], [EffectiveStartDate], [EffectiveEndDate]) VALUES (5, N'LCEC Invoice Approver SS', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Role] ([RoleID], [RoleName], [EffectiveStartDate], [EffectiveEndDate]) VALUES (6, N'LCEC Inspector SS', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Role] ([RoleID], [RoleName], [EffectiveStartDate], [EffectiveEndDate]) VALUES (7, N'LCEC Inspector SL', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Role] ([RoleID], [RoleName], [EffectiveStartDate], [EffectiveEndDate]) VALUES (8, N'LCEC Finance', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Role] ([RoleID], [RoleName], [EffectiveStartDate], [EffectiveEndDate]) VALUES (9, N'Vendor Staking AsBuilt', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Role] ([RoleID], [RoleName], [EffectiveStartDate], [EffectiveEndDate]) VALUES (10, N'Vendor Staking Invoicing', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Role] ([RoleID], [RoleName], [EffectiveStartDate], [EffectiveEndDate]) VALUES (11, N'Vendor Streetlight Invoicing', CAST(N'2018-07-01T00:00:00.000' AS DateTime), NULL)
GO
INSERT [sec].[Role] ([RoleID], [RoleName], [EffectiveStartDate], [EffectiveEndDate]) VALUES (12, N'LCEC IT Administration', CAST(N'2020-10-30T17:00:00.000' AS DateTime), NULL)
GO
SET IDENTITY_INSERT [sec].[Role] OFF
GO
SET IDENTITY_INSERT [sec].[UserRole] ON 
GO
INSERT [sec].[UserRole] ([UserRoleID], [UserID], [RoleID]) VALUES (224, 1, 1)
GO
INSERT [sec].[UserRole] ([UserRoleID], [UserID], [RoleID]) VALUES (225, 1, 2)
GO
INSERT [sec].[UserRole] ([UserRoleID], [UserID], [RoleID]) VALUES (226, 1, 3)
GO
INSERT [sec].[UserRole] ([UserRoleID], [UserID], [RoleID]) VALUES (227, 1, 4)
GO
INSERT [sec].[UserRole] ([UserRoleID], [UserID], [RoleID]) VALUES (228, 1, 5)
GO
INSERT [sec].[UserRole] ([UserRoleID], [UserID], [RoleID]) VALUES (229, 1, 6)
GO
INSERT [sec].[UserRole] ([UserRoleID], [UserID], [RoleID]) VALUES (230, 1, 7)
GO
INSERT [sec].[UserRole] ([UserRoleID], [UserID], [RoleID]) VALUES (231, 1, 8)
GO
INSERT [sec].[UserRole] ([UserRoleID], [UserID], [RoleID]) VALUES (232, 1, 9)
GO
INSERT [sec].[UserRole] ([UserRoleID], [UserID], [RoleID]) VALUES (233, 1, 10)
GO
INSERT [sec].[UserRole] ([UserRoleID], [UserID], [RoleID]) VALUES (234, 1, 11)
GO
INSERT [sec].[UserRole] ([UserRoleID], [UserID], [RoleID]) VALUES (235, 1, 12)
GO
SET IDENTITY_INSERT [sec].[UserRole] OFF
GO
SET IDENTITY_INSERT [dbo].[AlertNotification] ON 
GO
INSERT [dbo].[AlertNotification] ([AlertNotificationId], [Title], [Body], [EffectiveStartDt], [EffectiveEndDt]) VALUES (1, N'System Maintenance', N'We will be deploying an update to version 1.3.5 that corrects a few minor issues related to sorting on the as built edit page. Maintenance will begin at 12:00pm today please logout before that time.', CAST(N'2020-11-13T10:03:49.803' AS DateTime), CAST(N'2020-11-13T12:45:00.000' AS DateTime))
GO
INSERT [dbo].[AlertNotification] ([AlertNotificationId], [Title], [Body], [EffectiveStartDt], [EffectiveEndDt]) VALUES (2, N'System Outage', N'Please save any work you may have opened and please logout. IT needs to restart this server.', CAST(N'2019-05-16T10:00:00.000' AS DateTime), CAST(N'2019-05-16T11:00:00.000' AS DateTime))
GO
INSERT [dbo].[AlertNotification] ([AlertNotificationId], [Title], [Body], [EffectiveStartDt], [EffectiveEndDt]) VALUES (3, N'Maintenance', N'IT will be performing maintenance on VendorPortal this afternoon starting at 4:30. Please save your work and logout before that time.', CAST(N'2019-05-23T10:00:00.000' AS DateTime), CAST(N'2019-05-23T17:30:00.000' AS DateTime))
GO
INSERT [dbo].[AlertNotification] ([AlertNotificationId], [Title], [Body], [EffectiveStartDt], [EffectiveEndDt]) VALUES (4, N'Maintenance Today', N'We will be deploying bugs fixes 12pm today to fix a few bugs reported by the Inspectors. Please logout of VendorPortal by 11:50am. Thank you', CAST(N'2020-11-02T10:46:04.477' AS DateTime), CAST(N'2020-11-02T12:15:00.000' AS DateTime))
GO
SET IDENTITY_INSERT [dbo].[AlertNotification] OFF
GO
SET IDENTITY_INSERT [sec].[UserTbl] ON 
GO
INSERT [sec].[UserTbl] ([UserID], [UserName], [IsLocked], [FirstName], [LastName], [Email], [Phone], [PhoneCarrier], [Password], [PasswordSalt], [LastLogin], [WorkGroup], [EffectiveStartDate], [EffectiveEndDate], [CreatedBy]) VALUES (1, N'lcecadmin', 0, N'Admin', N'User', N'test.account@lcec.net', N'(239) 656-2300', N'Verizon', N'$shiro1$SHA-256$500000$8ItcvRxwyO2EpARltVw81w==$85iKxpv0rMu/jni/u74xBu7VtlVt9V+P3R3bsRdurJI=', N'ddIPi7Orw86ZhCZSBMQ0UA==', CAST(N'2020-12-18T08:52:26.720' AS DateTime), N'LCEC', CAST(N'2018-07-30T08:16:27.063' AS DateTime), NULL, NULL)
GO
SET IDENTITY_INSERT [sec].[UserTbl] OFF
GO
